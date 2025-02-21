package com.example.test.di.api_module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class RetrofitModule_ProvideRetrofitPrayerTimesFactory implements Factory<Retrofit> {
  @Override
  public Retrofit get() {
    return provideRetrofitPrayerTimes();
  }

  public static RetrofitModule_ProvideRetrofitPrayerTimesFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Retrofit provideRetrofitPrayerTimes() {
    return Preconditions.checkNotNullFromProvides(RetrofitModule.INSTANCE.provideRetrofitPrayerTimes());
  }

  private static final class InstanceHolder {
    private static final RetrofitModule_ProvideRetrofitPrayerTimesFactory INSTANCE = new RetrofitModule_ProvideRetrofitPrayerTimesFactory();
  }
}
