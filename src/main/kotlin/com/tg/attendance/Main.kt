package com.tg.attendance

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Guice
import com.google.inject.Injector
import com.tg.attendance.dto.AttendanceDto
import com.tg.attendance.dto.AttendanceSummaryOutput
import com.tg.attendance.dto.BanyuanDto
import com.tg.attendance.exception.BanyuanNotFoundException
import com.tg.attendance.module.AttendanceModule
import com.tg.attendance.module.BanyuanModule
import com.tg.attendance.service.AttendancePersistanceService
import com.tg.attendance.service.BanyuanPersistanceService
import org.eclipse.jetty.server.*
import org.eclipse.jetty.util.ssl.SslContextFactory
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.*
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.*

/**
 * To generate Jooq, under codegen folder
 * java -classpath ./jooq-3.14.16.jar:./jooq-meta-3.14.16.jar:./jooq-codegen-3.14.16.jar:./reactive-streams-1.0.3.jar:./r2dbc-spi-0.9.1.RELEASE.jar:./jakarta.xml.bind-api-3.0.0.jar:./mysql-connector-java-8.0.29.jar:./jaxb-api-2.3.1.jar:. org.jooq.codegen.GenerationTool library.xml
 */

//Guice Map Beans

val injector: Injector = Guice.createInjector(AttendanceModule(), BanyuanModule());
var attendanceService: AttendancePersistanceService = injector.getInstance(AttendancePersistanceService::class.java)
var banyuanPersistanceService: BanyuanPersistanceService = injector.getInstance(BanyuanPersistanceService::class.java)

val markAttendanceLens = Body.auto<String>().toLens()
val takeLeaveLens = Body.auto<String>().toLens()
val updateBanyuanQrCodeLens = Body.auto<String>().toLens()
val updateBanyuanLens = Body.auto<BanyuanDto>().toLens()
val createBanyuanLens = Body.auto<String>().toLens()
val attendanceLens = Body.auto<List<AttendanceSummaryOutput>>().toLens()
val allBanyuanLens = Body.auto<List<BanyuanDto>>().toLens()
val findBanyuanByIdLens = Body.auto<BanyuanDto>().toLens()

val app: HttpHandler = routes(
    "/ping" bind GET to {
        Response(OK).body("pong")
    },
    "/mark/{bentang}/{id}" bind GET to {
            req: Request -> markAttendanceLens.inject(mark(req.path("id").toString(), req.path("bentang").toString()), Response(Status.OK))
    },
    "/takeleave/{bentang}/{id}/{reason}" bind GET to {
            req: Request -> takeLeaveLens.inject(takeLeave(req.path("id").toString(), req.path("bentang").toString(), req.path("reason").toString()), Response(Status.OK))
    },
    "/banyuan/qrcode/{oldCode}/{newCode}" bind Method.PATCH to {
        req: Request -> updateBanyuanQrCodeLens.inject(updateQRCode(req.path("oldCode").toString(), req.path("newCode").toString()), Response(Status.OK))
    },
    "/banyuan" bind Method.PATCH to {
            req: Request -> createBanyuanLens.inject(updateBanyuan(req.query("id").toString(),
        req.query("qrcode").toString(), req.query("name").toString(),req.query("gender").toString(),
        req.query("deshu").toString(), req.query("fotang").toString(),
        req.query("tianzhi").toString(), req.query("jcbCompletion").toString(), req.query("sn").toString(), req.query("tel").toString(),
        req.query("status").toString(), req.query("remark").toString()
    ), Response(Status.OK))
    },

    "/banyuan" bind Method.POST to {
            req: Request -> createBanyuanLens.inject(createBanyuan(
                req.query("qrcode").toString(), req.query("name").toString(),req.query("gender").toString(),
        req.query("deshu").toString(), req.query("fotang").toString(),
                req.query("tianzhi").toString(), req.query("jcbCompletion").toString(), req.query("sn").toString(), req.query("tel").toString(),
               req.query("status").toString(), req.query("remark").toString()
            ), Response(Status.OK))
    },
    "/attendanceSummary/{bentang}" bind GET to allAttendance(),
    "/banyuan/all/{bentang}" bind GET to allBanyuans(),
    "/banyuan/{id}" bind GET to findBanyuanById()
)

class SecureJetty(
    private val sslPort: Int,
    private val localKeyStorePath: String,
    private val localKeystorePassword: String,
    private val locakKeyManagerPassword: String

) : ServerConfig {
    override fun toServer(http: HttpHandler): Http4kServer {
        val server = Server().apply {
            val https = HttpConfiguration().apply {
                addCustomizer(SecureRequestCustomizer(false))
            }

            val sslContextFactory = SslContextFactory.Server().apply {
                this.keyStorePath = localKeyStorePath
                setKeyStorePassword(localKeystorePassword)
                setKeyManagerPassword(locakKeyManagerPassword)
            }

            connectors = arrayOf(
                ServerConnector(server,
                    SslConnectionFactory(sslContextFactory, "http/1.1"),
                    HttpConnectionFactory(https)).apply { port = sslPort }
            )

            insertHandler(http.toJettyHandler())
        }

        return object : Http4kServer {
            override fun start(): Http4kServer = apply { server.start() }

            override fun stop(): Http4kServer = apply { server.stop() }

            override fun port(): Int = if (sslPort > 0) sslPort else server.uri.port
        }
    }
}

fun main() {
    System.setProperty("org.jooq.no-logo", "true")

    var serverFilter =
        ServerFilters.Cors(
        CorsPolicy(
        OriginPolicy.AllowAll(),
            listOf("Content-Type"),
            listOf(GET, Method.POST, Method.PATCH, Method.DELETE
    ))
    ).then (app)
    val compositeFilter = DebuggingFilters.PrintRequest().then(serverFilter)

    //PrintRequestAndResponse().then (app)
    compositeFilter.asServer(SunHttp(4011)).start()
//        .asServer(
//            SecureJetty(
//                4011, "keystore.jks",
//                "password", "password"
//            ),
//        ).start()
}

fun mark(id: String, bentang: String): String {
    try {
        return attendanceService.mark(AttendanceDto(id, bentang))
    } catch (exception: BanyuanNotFoundException) {
        return exception.message.toString()
    }
}

fun takeLeave(id: String, bentang: String, reason: String): String {
    try {
        return attendanceService.takeLeave(AttendanceDto(id, bentang, reason))
    } catch (exception: BanyuanNotFoundException) {
        return exception.message.toString()
    }
}

fun updateQRCode(oldCode: String, newCode: String): String {
    try {
        return banyuanPersistanceService.updateBanyuanQRCode(oldCode, newCode)
    } catch (exception: BanyuanNotFoundException) {
        return exception.message.toString()
    }
}

fun updateBanyuan(id: String, qrcode: String, name: String, gender: String, deshu: String, fotang: String, tianzhi: String,
jcbCompletion: String, sn: String, tel: String, status: String, remark: String): String {
    try {
    val result = banyuanPersistanceService.updateBanyuan(BanyuanDto(id,
        qrcode, name, gender, deshu, fotang, tianzhi, jcbCompletion, sn, tel, status, remark))
        return result;
    } catch (exception: Exception) {
        exception.printStackTrace()
        return "-1";
    }
}

fun createBanyuan(qrcode: String, name: String, gender: String, deshu: String, fotang: String, tianzhi: String,
jcbCompletion: String, sn: String, tel: String, status: String, remark: String): String {
    try {
       var result = banyuanPersistanceService.createBanyuan(BanyuanDto(
           qrcode, name, gender, deshu, fotang, tianzhi, jcbCompletion, sn, tel, status, remark
       ));
        return result;
    } catch (exception: Exception) {
       exception.printStackTrace()
       return "-1";
    }
}

fun allAttendance(): HttpHandler = {
    try {
         Response(OK).with(
                attendanceLens of attendanceService.attendanceSummary(it.path("bentang")))
    } catch (exception: BanyuanNotFoundException) {
        Response(NOT_FOUND)
    }
}

fun allBanyuans(): HttpHandler = {
    Response(OK).with(
            allBanyuanLens of banyuanPersistanceService.fetchAllBanyuans(it.path("bentang")))
}

fun findBanyuanById(): HttpHandler = {
    try {
    Response(OK).with(
        findBanyuanByIdLens of banyuanPersistanceService.findBanyuanById(it.path("id")))
    } catch (exception: BanyuanNotFoundException) {
        Response(NOT_FOUND)
    }
}