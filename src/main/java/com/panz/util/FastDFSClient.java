package com.panz.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer,   storageServer);
    }

    /**
     * �ϴ��ļ�����
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName �ļ�ȫ·��
     * @param extName �ļ���չ������������.��
     * @param metas �ļ���չ��Ϣ
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    /**
     * ɾ���ļ�����
     * @param fileId
     * @return
     * @throws Exception
     */
    public int deleteFile(String fileId) throws Exception {
        int ret = storageClient.delete_file1(fileId);
        return ret;
    }


    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * �ϴ��ļ�����
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent �ļ������ݣ��ֽ�����
     * @param extName �ļ���չ��
     * @param metas �ļ���չ��Ϣ
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }
}