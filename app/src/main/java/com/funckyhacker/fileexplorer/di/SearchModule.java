package com.funckyhacker.fileexplorer.di;

import android.app.Activity;
import com.funckyhacker.fileexplorer.view.search.SearchActivity;
import com.funckyhacker.fileexplorer.view.search.SearchViewModel;
import com.funckyhacker.fileexplorer.view.search.SearchViewModelImpl;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchModule {
  @Binds @IntoMap @ActivityKey(SearchActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindInjectorFactory(
      SearchComponent.Builder builder);

  @Binds abstract SearchViewModel bindMainViewModel(SearchViewModelImpl viewModel);

}