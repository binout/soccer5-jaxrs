package net.binout.soccer5;

import com.google.common.collect.Sets;
import net.binout.soccer5.resources.HomeResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * User: binout
 * Date: 27/07/13
 */
@ApplicationPath(Soccer5Application.ROOT_PATH)
public class Soccer5Application extends Application {

    static final String ROOT_PATH = "/";

    @Override
    public Set<Class<?>> getClasses() {
        return Sets.newHashSet(new Class<?>[]{HomeResource.class});
    }
}
