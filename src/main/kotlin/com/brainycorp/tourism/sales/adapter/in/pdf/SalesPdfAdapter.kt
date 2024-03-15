package com.brainycorp.tourism.sales.adapter.`in`.pdf

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse
import com.brainycorp.tourism.sales.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.shared.models.PdfPrinter
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream

@RestController
@RequestMapping("/sales")
class SalesPdfAdapter(
    val pdfPrinter: PdfPrinter<SaleResponse>,
    val searchSalesQuery: SearchSalesQuery
) {
    val log = LoggerFactory.getLogger("SalesPdfAdapter")
    @GetMapping("/pdf")
    @CrossOrigin("*")
    fun downloadPdf(response: HttpServletResponse, @RequestParam searcher: String){
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=clients.pdf")

            val sales: List<SaleResponse> = searchSalesQuery.execute(searcher)

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(sales, outputStream) { document, sale ->
                val table = Table(floatArrayOf(1f, 3f))

                table.addCell(Cell().add(Paragraph("Código de Venta:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${sale.numSale ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Método de Pago:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${sale.paymentMethod ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                table.addCell(Cell().add(Paragraph("Costo:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${sale.cost ?: "N/A"}").setFontColor(ColorConstants.RED)))

                table.addCell(Cell().add(Paragraph("Cliente:").setFontColor(ColorConstants.BLACK).setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                table.addCell(Cell().add(Paragraph("${sale.client?.email ?: "N/A"}").setFontColor(ColorConstants.BLACK)))

                document.add(table)
                document.add(Paragraph("\n")) // Espacio entre registros
            }
            response.outputStream.write(outputStream.toByteArray())
            response.flushBuffer()
        } catch (e: Exception) {
            log.error(e.message)
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }
}