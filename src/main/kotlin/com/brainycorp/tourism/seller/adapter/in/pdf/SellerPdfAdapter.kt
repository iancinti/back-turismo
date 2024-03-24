package com.brainycorp.tourism.seller.adapter.`in`.pdf

import com.brainycorp.tourism.shared.models.PdfPrinter
import com.brainycorp.tourism.seller.application.port.`in`.SearchSellerQuery
import com.brainycorp.tourism.seller.domain.Seller
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream

@RestController
@RequestMapping("/sellers")
class SellerPdfAdapter(
    val pdfPrinter: PdfPrinter<Seller>,
    val searchSellerQuery: SearchSellerQuery
) {
    val log = LoggerFactory.getLogger("SellerPdfAdapter")

    @GetMapping("/pdf")
    @CrossOrigin("*")
    fun downloadPdf(response: HttpServletResponse, @RequestParam searcher: String) {
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=sellers.pdf")

            val sellers: List<Seller> = searchSellerQuery.execute(searcher)

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(sellers, outputStream) { document, seller ->
                val table = Table(floatArrayOf(1f, 3f))

                table.addCell(Cell().add(Paragraph("ID:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.id?.toInt() ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Nombre:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.name ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Apellido:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.lastname ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("DNI:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.dni ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Fecha de nacimiento:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.birthday ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Nacionalidad:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.nationality ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Teléfono celular:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.cellPhone ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Correo electrónico:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.email ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Cargo:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.charge ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Salario:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${seller.salary?.toDouble() ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                document.add(table)
                document.add(Paragraph("\n"))
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error("Error al generar el PDF de los vendedores: ${e.message}")
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}
