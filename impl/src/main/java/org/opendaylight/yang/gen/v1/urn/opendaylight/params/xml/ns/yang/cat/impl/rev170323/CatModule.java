package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.impl.rev170323;

import com.whu.odl.exampleapp.impl.CatProvider;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.CatService;

public class CatModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.impl.rev170323.AbstractCatModule {
    public CatModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public CatModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.impl.rev170323.CatModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        final CatProvider catProvider = new CatProvider(getDataBrokerDependency(),getRpcRegistryDependency());
        final BindingAwareBroker.RpcRegistration<CatService> catServiceRpcRegistration = getRpcRegistryDependency().addRpcImplementation(CatService.class,catProvider);
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                catProvider.close();
                catServiceRpcRegistration.close();
            }
        };
        //return catProvider;
    }

}
