package com.brainycorp.tourism.sales.adapter.`in`.pdf

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse
import com.brainycorp.tourism.sales.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.shared.models.PdfPrinter
import com.itextpdf.layout.element.Paragraph
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
                document.add(Paragraph("Codigo de Venta: ${sale.numSale ?: "N/A"}"))
                document.add(Paragraph("Metodo de Pago: ${sale.paymentMethod ?: "N/A"}"))
                document.add(Paragraph("Costo: ${sale.cost ?: "N/A"}"))
                document.add(Paragraph("Cliente: ${sale.client?.email ?: "N/A"}"))
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