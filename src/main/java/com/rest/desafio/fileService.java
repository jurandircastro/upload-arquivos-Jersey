package com.rest.desafio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("admin")
public class fileService {
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile( @FormDataParam("filedata") InputStream fileInputStream,
		    					@FormDataParam("file") FormDataContentDisposition fileDetail)  {
		String filePath = "C:\\Users\\Jusan\\Desktop\\uploads\\" + fileDetail.getFileName();
		saveFile(fileInputStream, filePath);
		String output = "File saved to server location : " + filePath;
		return Response.status(200).entity(output).build();
	}

	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
		try {	
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			System.out.println(uploadedInputStream.toString());
			outpuStream = new FileOutputStream(new File(serverLocation));
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}