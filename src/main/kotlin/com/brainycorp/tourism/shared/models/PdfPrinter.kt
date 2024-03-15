package com.brainycorp.tourism.shared.models

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import org.springframework.stereotype.Component
import java.io.OutputStream

@Component
class PdfPrinter<T> {

    fun printToPdf(elements: List<T>, outputStream: OutputStream, printer: (Document, T) -> Unit) {
        try {
            val pdfWriter = PdfWriter(outputStream)
            val pdfDocument = PdfDocument(pdfWriter)
            val document = Document(pdfDocument)
            for (el in elements) {
                printer(document, el)
            }
            document.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
