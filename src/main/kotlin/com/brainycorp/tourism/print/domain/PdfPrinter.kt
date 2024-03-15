package com.brainycorp.tourism.print.domain

import com.brainycorp.tourism.client.domain.Client
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import org.springframework.stereotype.Component
import java.io.OutputStream

@Component
class PdfPrinter {

    fun printToPdf(clients: List<Client>, outputStream: OutputStream, printer: (Document, Client) -> Unit) {
        try {
            val pdfWriter = PdfWriter(outputStream)
            val pdfDocument = PdfDocument(pdfWriter)
            val document = Document(pdfDocument)

            for (client in clients) {
                printer(document, client)
            }

            document.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
