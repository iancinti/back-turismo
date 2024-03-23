package com.brainycorp.tourism.service.adapter.`in`.pdf

import com.brainycorp.tourism.shared.models.PdfPrinter
import com.brainycorp.tourism.service.application.port.`in`.SearchServicesQuery
import com.brainycorp.tourism.service.domain.Service
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
@RequestMapping("/services")
class ServicePdfAdapter(
    val pdfPrinter: PdfPrinter<Service>,
    val searchServicesQuery: SearchServicesQuery
) {
    val log = LoggerFactory.getLogger("ServicePdfAdapter")

    @GetMapping("/pdf")
    @CrossOrigin("*")
    fun downloadPdf(
        response: HttpServletResponse,
        @RequestParam searcher: String,
        @RequestParam(required = false) typeId: String?
    ) {
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "attachment; filename=services.pdf")

            val services: List<Service> = searchServicesQuery.execute(searcher, typeId)

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(services, outputStream) { document, service ->
                val table = Table(floatArrayOf(1f, 3f))

                table.addCell(Cell().add(Paragraph("Código:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.code?.toInt() ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Tipo:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.type ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Descripción:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.description ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Destino:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.destination ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Costo:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.cost?.toDouble() ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Pic:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${service.pic ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                document.add(table)
                document.add(Paragraph("\n"))
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error("Error al generar el PDF de los servicios: ${e.message}")
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}

