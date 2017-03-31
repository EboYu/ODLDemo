/*
 * Copyright © 2017 Copyright (c) 2017 Yinbo and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.whu.odl.exampleapp.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.AnimalService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeCatInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeChickenInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeDogInput;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;

import java.util.concurrent.Future;

public class AnimalImpl implements AnimalService{

    private final DataBroker dataBroker;
    private final RpcProviderRegistry rpcRegistry;
    private static final Future<RpcResult<Void>> RPC_SUCCESS = RpcResultBuilder.<Void>success().buildFuture();

    public AnimalImpl(DataBroker dataBroker, RpcProviderRegistry rpcRegistry){
        this.dataBroker =dataBroker;
        this.rpcRegistry = rpcRegistry;
    }

    @Override
    public Future<RpcResult<Void>> makeCat(MakeCatInput input) {

        StringBuffer sb = new StringBuffer();
        sb.append("Trace ID: ");
        sb.append(input.getTraceID());
        print(sb.toString());
        for(long i=0; i<input.getNum();i++){
            sleep(100);
        }
        return RPC_SUCCESS;
    }

    @Override
    public Future<RpcResult<Void>> makeChicken(MakeChickenInput input) {
        StringBuffer sb = new StringBuffer();
        sb.append("Trace ID: ");
        sb.append(input.getTraceID());
        print(sb.toString());
        for(long i=0; i<input.getNum();i++){
            sleep(80);
        }
        return RPC_SUCCESS;
    }

    @Override
    public Future<RpcResult<Void>> makeDog(MakeDogInput input) {
        StringBuffer sb = new StringBuffer();
        sb.append("Trace ID: ");
        sb.append(input.getTraceID());
        print(sb.toString());
        for(long i=0; i<input.getNum();i++){
            sleep(120);
        }
        return RPC_SUCCESS;
    }


    public void print(String s){
        System.out.println(s+" in Thread: "+Thread.currentThread().getId());
    }

    public void sleep(long time){
        try {
            Thread.sleep(time);

        } catch (InterruptedException e) {

        }
    }
    public void close() throws Exception {

    }
}
