package org.kevin.service;

import org.apache.logging.log4j.core.jackson.ContextDataAsEntryListSerializer;
import org.kevin.dao.MCUHistoryDao;
import org.kevin.dao.MCUOpInfoDao;
import org.kevin.domain.WSNotifyType;
import org.kevin.service.base.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.kevin.common.Commons.*;

@Service
public class SocketService2 extends ServiceBase {
    MCUOpInfoDao mMCUOpInfoDao;
    MCUHistoryDao mMCUHistoryDao;

    WebSocketService mWebSocketService;

    private ServerSocket mServerSocket;
    private boolean mStarted;
    private Integer mPort = 2800;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @Autowired
    public void setMCUHistoryDao(MCUHistoryDao MCUHistoryDao) {
        mMCUHistoryDao = MCUHistoryDao;
    }

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }



    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        mWebSocketService = webSocketService;
    }

    public void start() {
        System.out.println("Running");
        start(2800);
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

        while (mStarted) {
            try {
                Socket socket = mServerSocket.accept();
                Runnable runnable = () -> {
                    try{
                        System.out.println("Sockect client connected");
                        byte[] messages = onMessage(socket);
                        //IResponse response = handleUseCase(new MCURequest(msg));
                        //if (response != null)
                        //sendMessage(socket, response);
                        onProcessMessageType2(messages);
                        socket.close();
                    }
                    catch (IOException e)
                    {
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
    private static byte[] onMessage(Socket socket) {
        byte[] content = new byte[200];
        int len;
        try {
            // after connection is established, get socket input stream from socket, read messages from buffer
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if ((len = inputStream.read(content)) >0) {
                // please use same encoding between sender and receiver,recommended by using UTF-8 encoding.
                baos.write(content,0,len);
            }
            // shutdown server side output stream
            socket.shutdownInput();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }



    void onProcessMessageType2(byte[] packet){
            if(!validatePackage(packet))
                return;

            String mcuId = Integer.valueOf ((int)packet[4]).toString();

            mMCUOpInfoDao.updateMCUOpInfo(mcuId,packet[5],packet[6],packet[7],packet[8],packet[9],new Date());
            for(int i =4; i<packet.length;i++){
                if(packet[i] != 0)
                {
                    System.out.println("MCUId:" +  mcuId + "1:" + packet[5] + "2:" +
                            packet[6] + "3:" + packet[7] + "4:" + packet[8] + "5:" + packet[9]);
                    mMCUHistoryDao.insertMCUHistory(mcuId,packet[5],packet[6],packet[7],packet[8],packet[9],new Date());
                    return;
                }
            }
    }

    boolean validatePackage(byte[] packet){
        //Check length
        if(packet.length != PACKET_LENGTH) {
            System.out.println("Message length is mismatch");
            for(byte i : packet){
                System.out.print(i);
            }
            System.out.println();
            return false;
        }

        int checksum = 0;

        //Check header
        for (int i = 0; i < PACKET_LENGTH; i++) {
            if(i < HEADER_LENGTH)
            {
                if (packet[i] != HEADER[i]) {
                    System.out.println("Header is not correct, drop package!");
                    return false;
                }
            }
            else if (i< PACKET_PAYLOAD)
            {
                checksum += packet[i];
            }
            else if (i < PACKET_CHECKSUM)
            {
                //checksum
                if(checksum % 256 != packet[i])
                    return false;
                else
                    break;
            }
        }

        if(packet[PACKET_LENGTH - 1] != 0x16)
            return false;

        return true;
    }

    void onProcessMessage(byte[] bytes){
        if(bytes.length != 6) {
            System.out.println("Message length is mismatch");
            for(byte i : bytes){
                System.out.print(i);
            }
            System.out.println();
        }
        else
        {

            String mcuId = Integer.valueOf ((int)bytes[0]).toString();

            mMCUOpInfoDao.updateMCUOpInfo(mcuId,bytes[1],bytes[2],bytes[3],bytes[4],bytes[5],new Date());
            for(int i =1; i<bytes.length;i++){
                if(bytes[i] != 0)
                {
                    System.out.println("MCUId:" +  mcuId + "1:" + bytes[1] + "2:" +
                            bytes[2] + "3:" + bytes[3] + "4:" + bytes[4] + "5:" + bytes[5]);
                    mWebSocketService.notifyUserInfo(mcuId, WSNotifyType.ALERT);
                    mMCUHistoryDao.insertMCUHistory(mcuId,bytes[1],bytes[2],bytes[3],bytes[4],bytes[5],new Date());
                    return;
                }
            }
        }
    }
}
