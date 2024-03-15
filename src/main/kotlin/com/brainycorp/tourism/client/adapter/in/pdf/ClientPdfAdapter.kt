package com.brainycorp.tourism.client.adapter.`in`.pdf

import com.brainycorp.tourism.shared.models.PdfPrinter
import com.brainycorp.tourism.client.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.client.domain.Client
import com.itextpdf.layout.element.Paragraph
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream

@RestController
@RequestMapping("/clients")
class ClientPdfAdapter(
    val pdfPrinter: PdfPrinter<Client>,
    val searchClientQuery: SearchClientQuery
) {
    val log = LoggerFactory.getLogger("ClientPdfAdapter")

    @GetMapping("/pdf")
    @CrossOrigin("*")
    fun downloadPdf(response: HttpServletResponse, @RequestParam searcher: String) {
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=clients.pdf")

            val clients: List<Client> = searchClientQuery.execute(searcher)

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(clients, outputStream) { document, client ->
                document.add(Paragraph("ID: ${client.id?.toInt() ?: "N/A"}"))
                document.add(Paragraph("Nombre: ${client.name ?: "N/A"}"))
                document.add(Paragraph("Apellido: ${client.lastname ?: "N/A"}"))
                document.add(Paragraph("DNI: ${client.dni ?: "N/A"}"))
                document.add(Paragraph("Fecha de nacimiento: ${client.birthday ?: "N/A"}"))
                document.add(Paragraph("Nacionalidad: ${client.nationality ?: "N/A"}"))
                document.add(Paragraph("Teléfono celular: ${client.cellPhone ?: "N/A"}"))
                document.add(Paragraph("Correo electrónico: ${client.email ?: "N/A"}"))
                document.add(Paragraph("\n"))
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error(e.message)
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}