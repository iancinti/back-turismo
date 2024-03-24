package com.brainycorp.tourism.packagee.adapter.`in`.pdf

import com.brainycorp.tourism.shared.models.PdfPrinter
import com.brainycorp.tourism.packagee.application.port.`in`.SearchPackagesQuery
import com.brainycorp.tourism.packagee.domain.Package
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
@RequestMapping("/packages")
class PackagePdfAdapter(
    val pdfPrinter: PdfPrinter<Package>,
    val searchPackagesQuery: SearchPackagesQuery
) {
    val log = LoggerFactory.getLogger("PackagePdfAdapter")

    @GetMapping("/pdf")
    @CrossOrigin("*")
    fun downloadPdf(response: HttpServletResponse, @RequestParam searcher: String) {
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=packages.pdf")

            val packages: List<Package> = searchPackagesQuery.execute(searcher)

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(packages, outputStream) { document, packag ->
                val table = Table(floatArrayOf(1f, 3f))

                table.addCell(Cell().add(Paragraph("Code:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${packag.code ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Nombre:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${packag.name ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Destino:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${packag.destination ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Costo:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${packag.cost ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("pic:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${packag.pic ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                document.add(table)
                document.add(Paragraph("\n"))
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error("Error al generar el PDF de los paquetes: ${e.message}")
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}

