import java.net.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MJPG extends HttpServlet {

	// source vars
	DatagramSocket sourceSocket = null;
	int sourcePort = 49199;
    private InetAddress localIP;

    /**
     * Constructor
     * @see HttpServlet#HttpServlet()
     */
    public MJPG() {
        super();
		try {
			localIP = InetAddress.getLocalHost();
			sourceSocket = new DatagramSocket(sourcePort);
			System.out.println("Listening on UDP port: " + sourcePort + " on local address " + localIP.getHostAddress());
		} catch (SocketException ExceSocket) {
			System.out.println("Exception when opening UDP socket: "+ ExceSocket.getMessage());
		} catch(UnknownHostException e) {
			System.out.println("Local IP not found..?");
		}
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// MJPG Content-type
		response.setContentType("multipart/x-mixed-replace; boundary=--BoundaryString");

		OutputStream outputStream = response.getOutputStream();

		DatagramPacket incomingPacket;
		byte[] rewriteBuffer;
		byte[] inputBuffer;
        int offset=132;

		inputBuffer = new byte[30000];

		while(true) {
			try {
				incomingPacket = new DatagramPacket(inputBuffer, inputBuffer.length, localIP, sourcePort);
                sourceSocket.receive(incomingPacket);
				rewriteBuffer = incomingPacket.getData();
            	for (int i = 130; i < 320; i += 1) {
                	if (rewriteBuffer[i]==-1 && rewriteBuffer[i+1]==-40) {
                    	offset = i;
                	}
                }
                byte [] outputBuffer = Arrays.copyOfRange( rewriteBuffer, offset, incomingPacket.getLength() );

				outputStream.write((
					"--BoundaryString\r\n" +
					"Content-type: image/jpeg\r\n" +
					"Content-Length: " +
					outputBuffer.length +
					"\r\n\r\n").getBytes());
				outputStream.write(outputBuffer);
				outputStream.write("\r\n\r\n".getBytes());
				outputStream.flush();

				// Sleep to not flood browser.
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (Exception e) {
				System.out.println("Exception hit: " + e.getMessage());
				return;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			doGet(request, response);
	}
}
