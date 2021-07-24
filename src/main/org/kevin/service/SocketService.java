package org.kevin.service;


import org.kevin.service.interfaces.IRequest;
import org.kevin.service.interfaces.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class SocketService {

    MCUSessionMgr mMCUSessionMgr;

    @Autowired
    public void setMCUSessionMgr(MCUSessionMgr MCUSessionMgr) {
        mMCUSessionMgr = MCUSessionMgr;
    }

    private ServerSocket mServerSocket;
    private boolean mStarted;
    private Integer mPort;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * Start socket server
     */
    public void start() {
        start(8090);
    }

    /**
     * Start socket server by using specified port
     *
     * @param port specified port number
     */
    private void start(Integer port) {
        try {
            mServerSocket = new ServerSocket(port == null ? this.mPort : port);
            mStarted = true;

        } catch (IOException e) {
            System.exit(0);
        }

        System.out.println(String.format("Socket Server is running ,%s", new Date()));

        while (mStarted) {
            try {
                Socket socket = mServerSocket.accept();
                Runnable runnable = () -> {
                    try {
                        StringBuilder msg = onMessage(socket);
                        IResponse response = handleUseCase(new MCURequest(msg));
                        if (response != null)
                            sendMessage(socket, response);
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };

                Future future = threadPool.submit(runnable);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    /**
     * Retrieve socket messages
     *
     * @param socket working socket
     * @return message retrieved
     */
    private static StringBuilder onMessage(Socket socket) {
        byte[] bytes = new byte[1024];
        int len;
        try {
            // after connection is established, get socket input stream from socket, read messages from buffer
            InputStream inputStream = socket.getInputStream();
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                // please use same encoding between sender and receiver,recommended by using UTF-8 encoding.
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            // shutdown server side output stream
            socket.shutdownInput();
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <TRequest extends IRequest, TResponse extends IResponse> TResponse handleUseCase(TRequest request) {

        return (TResponse) mMCUSessionMgr.ProcessRequest(request);

    }


    /**
     * Send messages by specified socket
     *
     * @param socket   working socket
     * @param response message need to be sent
     */
    private static void sendMessage(Socket socket, IResponse response) {
        try {
            //get output stream from socket
            OutputStream outputStream = socket.getOutputStream();
            //get UTF-8 encoding messages
            byte[] sendBytes = response.getResponse().getBytes("UTF-8");
            //然后将消息的长度优先发送出去
            outputStream.write(sendBytes.length >> 8);
            outputStream.write(sendBytes.length);
            //然后将消息再次发送出去
            outputStream.write(sendBytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
