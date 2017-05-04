/*
 * Copyright © 2017 Copyright (c) 2017 Yinbo and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.whu.odl.exampleapp.impl;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.CatService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.MewoInput;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class CatProvider implements CatService{
    private static final Logger LOG = LoggerFactory.getLogger(CatProvider.class);

    private final DataBroker dataBroker;
    private final RpcProviderRegistry rpcRegistry;


    public CatProvider(DataBroker dataBroker, RpcProviderRegistry rpcRegistry){
        this.dataBroker =dataBroker;
        this.rpcRegistry = rpcRegistry;
    }
    public void close() throws Exception {
        LOG.info("CatProvider Closed");
    }

    @Override
    public Future<RpcResult<Void>> mewo(MewoInput input) {
        makeCat(input.getNum());
        //LOG.info("Make Cat");
        return Futures.immediateFuture(RpcResultBuilder.<Void>success().build());

    }

    private void makeCat(long num){
        for(long i=0; i<num;i++){
            try {
                LOG.info("Making Cat, Sleep for 1 Seconds");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Cat: {}", e);
            }
        }

    }
}

