package com.brainycorp.tourism.print.adapter.`in`.controller

import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.print.application.usecase.RetrivePdfClientsUseCase
import com.brainycorp.tourism.print.domain.PdfPrinter
import com.itextpdf.layout.element.Paragraph
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream

@RestController
@CrossOrigin("*")
class PrintControllerAdapter(
    val pdfPrinter: PdfPrinter,
    val retrivePdfClientsUseCase: RetrivePdfClientsUseCase
) {

    @GetMapping("/print/clients/pdf")
    fun downloadPdf(response: HttpServletResponse) {
        try {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=clients.pdf")

            val clients: List<Client> = retrivePdfClientsUseCase.execute()

            val outputStream = ByteArrayOutputStream()
            pdfPrinter.printToPdf(clients, outputStream) { document, client ->
                document.add(Paragraph("ID: ${client.id ?: "N/A"}"))
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
            e.printStackTrace()
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }

}
