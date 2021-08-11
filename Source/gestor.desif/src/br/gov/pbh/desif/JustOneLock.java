/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class JustOneLock {
    private String appName;
    private File file;
    private FileChannel channel;
    private FileLock lock;

    public JustOneLock(String appName) {
        this.appName = appName;
    }

    public boolean isAppActive() {
        try {
            this.file = new File(System.getProperty("user.home"), this.appName + ".tmp");
            this.channel = new RandomAccessFile(this.file, "rw").getChannel();
            try {
                this.lock = this.channel.tryLock();
            }
            catch (OverlappingFileLockException e) {
                this.closeLock();
                return true;
            }
            if (this.lock == null) {
                this.closeLock();
                return true;
            }
            Runtime.getRuntime().addShutdownHook(new Thread(){

                @Override
                public void run() {
                    JustOneLock.this.closeLock();
                    JustOneLock.this.deleteFile();
                }
            });
            return false;
        }
        catch (Exception e) {
            this.closeLock();
            return true;
        }
    }

    private void closeLock() {
        try {
            this.lock.release();
        }
        catch (Exception exception) {
            // empty catch block
        }
        try {
            this.channel.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void deleteFile() {
        try {
            this.file.delete();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

}

