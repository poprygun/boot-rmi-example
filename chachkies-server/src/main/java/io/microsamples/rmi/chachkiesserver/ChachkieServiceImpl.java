package io.microsamples.rmi.chachkiesserver;

import io.microsamples.rmi.Chachkie;
import io.microsamples.rmi.ChachkieService;
import org.jeasy.random.EasyRandom;

public class ChachkieServiceImpl implements ChachkieService {
    private EasyRandom easyRandom = new EasyRandom();
    @Override
    public Chachkie whereIsMyChachkie() {
        return easyRandom.nextObject(Chachkie.class);
    }
}
