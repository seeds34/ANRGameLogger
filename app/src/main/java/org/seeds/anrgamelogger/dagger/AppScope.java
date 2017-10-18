package org.seeds.anrgamelogger.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Tomas Seymour-Turner on 17/10/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
