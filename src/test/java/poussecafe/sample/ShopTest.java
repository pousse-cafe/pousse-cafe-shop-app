package poussecafe.sample;

import poussecafe.runtime.Runtime;
import poussecafe.shop.ShopBundle;
import poussecafe.test.PousseCafeTest;

public abstract class ShopTest extends PousseCafeTest {

    @Override
    protected Runtime.Builder runtimeBuilder() {
        return super.runtimeBuilder()
                .withBundle(ShopBundle.configure().defineAndImplementDefault().build());
    }
}
