package com.tg.attendance

import com.google.inject.Guice
import com.google.inject.Injector
import com.tg.attendance.dto.AttendanceDto
import com.tg.attendance.exception.BanyuanNotFoundException
import com.tg.attendance.module.AttendanceModule
import com.tg.attendance.module.BanyuanModule
import com.tg.attendance.service.AttendancePersistanceService
import org.eclipse.jetty.server.*
import org.eclipse.jetty.util.ssl.SslContextFactory
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters.PrintRequestAndResponse
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.*

/**
 * To generate Jooq, under codegen folder
 * java -classpath ./jooq-3.14.16.jar:./jooq-meta-3.14.16.jar:./jooq-codegen-3.14.16.jar:./reactive-streams-1.0.3.jar:./r2dbc-spi-0.9.1.RELEASE.jar:./jakarta.xml.bind-api-3.0.0.jar:./mysql-connector-java-8.0.29.jar:./jaxb-api-2.3.1.jar:. org.jooq.codegen.GenerationTool library.xml
 */


val attendanceLens = Body.auto<String>().toLens()

val app: HttpHandler = routes(
    "/ping" bind GET to {
        Response(OK).body("pong")
    },
    "/mark/{bentang}/{id}" bind GET to {
            req: Request -> attendanceLens.inject(mark(req.path("id").toString(), req.path("bentang").toString()), Response(Status.OK))
    }
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
    PrintRequestAndResponse().then (app)
        .asServer(SunHttp(4011)).start()
//        .asServer(
//            SecureJetty(
//                4011, "keystore.jks",
//                "password", "password"
//            ),
//        ).start()
}

fun mark(id: String, bentang: String): String {
    val injector: Injector = Guice.createInjector(AttendanceModule(), BanyuanModule());
    var attendanceService: AttendancePersistanceService = injector.getInstance(AttendancePersistanceService::class.java)
    try {
        return attendanceService.mark(AttendanceDto(id, bentang))
    } catch (exception: BanyuanNotFoundException) {
        return exception.message.toString()
    }
}