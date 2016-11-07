package com.tuniu.aop.unittest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.List;

public class CrudExample {

    public static void main(String[] args) throws Exception {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("10.10.31.230:2181")
                .retryPolicy(retryPolicy)
                .build();
        curatorFramework.start();

        TreeCache cache = TreeCache.newBuilder(curatorFramework, "/confCenter/config/ABT")
                .setCacheData(true).build().start();
        //ChildData childData = cache.getCurrentData("/confCenter/config/ABT/ibeplus/officeNo");
        //System.out.println(childData);

        //Thread.sleep(1000L);
        System.out.println("监听开始/zk........");

        Thread.sleep(1000l);

        TreeCacheListener plis = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("!!!!!" + event);
            }
        };

        //注册监听
        cache.getListenable().addListener(plis);



        System.in.read();
    }

    public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given ZNode with the given data
        client.create().forPath(path, payload);
    }

    public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL ZNode with the given data
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
    }

    public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given
        // data using Curator protection.
        return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
    }

    public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
        // set data for the given node
        client.setData().forPath(path, payload);
    }

    public static void setDataAsync(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this is one method of getting event/async notifications
        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                // examine event for details
            }
        };
        client.getCuratorListenable().addListener(listener);
        // set data for the given node asynchronously. The completion
        // notification
        // is done via the CuratorListener.
        client.setData().inBackground().forPath(path, payload);
    }

    public static void setDataAsyncWithCallback(CuratorFramework client, BackgroundCallback callback, String path, byte[] payload) throws Exception {
        // this is another method of getting notification of an async completion
        client.setData().inBackground(callback).forPath(path, payload);
    }

    public static void delete(CuratorFramework client, String path) throws Exception {
        // delete the given node
        client.delete().forPath(path);
    }

    public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
        // delete the given node and guarantee that it completes
        client.delete().guaranteed().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path) throws Exception {
        /**
         * Get children and set a watcher on the node. The watcher notification
         * will come through the CuratorListener (see setDataAsync() above).
         */
        return client.getChildren().watched().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher) throws Exception {
        /**
         * Get children and set the given watcher on the node.
         */
        return client.getChildren().usingWatcher(watcher).forPath(path);
    }
}