package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323;

import com.whu.odl.exampleapp.impl.AnimalImpl;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;

public class AnimalModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.AbstractAnimalModule {
    public AnimalModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public AnimalModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.AnimalModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        // TODO:implement
        final AnimalImpl animal = new AnimalImpl(getDataBrokerDependency(),getRpcRegistryDependency());
        final BindingAwareBroker.RpcRegistration<AnimalService> animalServiceRpcRegistration = getRpcRegistryDependency().addRpcImplementation(AnimalService.class,animal);
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                animal.close();
                animalServiceRpcRegistration.close();
            }
        };
    }

}
