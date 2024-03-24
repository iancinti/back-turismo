package com.brainycorp.tourism.client.adapter.`in`.pdf

import com.brainycorp.tourism.shared.models.PdfPrinter
import com.brainycorp.tourism.client.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.client.domain.Client
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import jakarta.servlet.http.HttpServletResponse
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
                val table = Table(floatArrayOf(1f, 3f))

                table.addCell(Cell().add(Paragraph("ID:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.id?.toInt() ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Nombre:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.name ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Apellido:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.lastname ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("DNI:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.dni ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Fecha de nacimiento:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.birthday ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Nacionalidad:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.nationality ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Teléfono celular:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.cellPhone ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Correo electrónico:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${client.email ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                document.add(table)
                document.add(Paragraph("\n"))
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error("Error al generar el PDF de los clientes: ${e.message}")
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}
