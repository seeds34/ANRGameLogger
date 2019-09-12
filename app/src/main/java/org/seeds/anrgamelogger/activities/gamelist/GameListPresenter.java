package org.seeds.anrgamelogger.activities.gamelist;



import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tomas Seymour-Turner on 31/07/2017.
 */

public class GameListPresenter{

    private static final String LOG_TAG = GameListPresenter.class.getSimpleName();

    public GameListView view;
    public GameListModel model;
    private final CompositeDisposable compositeSubscription = new CompositeDisposable();


    public GameListPresenter(GameListView v, GameListModel m){
        view = v;
        model = m;
    }

    public void onCreate() {

        model.databaseFirstTimeSetup();


        view.setData(model.getGameList(20));

        compositeSubscription.add(onItemClick());
        compositeSubscription.add(addNewGame());
        compositeSubscription.add(addRepeatedGame());
        compositeSubscription.add(observerImageLoadButtonClick());
        compositeSubscription.add(observePurgeDB());
        compositeSubscription.add(observeExportDB());
        compositeSubscription.add(observeImportDB());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Disposable onItemClick(){
        return view.observeRecyclerView()
                .subscribe(pos ->{ model.startGameDetailActivity(pos.toString());
        });
    }

    private Disposable observerImageLoadButtonClick(){
        return view.download_images()
                .subscribe(__ -> model.loadIdentityImages());
    }


    private Disposable addNewGame(){
        return view.newLog().subscribe(__ -> {
                    model.startAddGameActivity();
                });
    }

    private Disposable addRepeatedGame(){
        return view.repeatLog().
                subscribe(__ -> {
                    model.startAddGameActivity(model.getLastUsedGameNo());
                });
    }

    public Disposable observePurgeDB(){
        return view.purgeDatabase()
                .doOnNext(__->model.purgeDatabse())
                .subscribe(__->this.refresh());
    }

    public Disposable observeExportDB(){
        return view.exportDatabase()
                .subscribe(__->model.exportDB());
    }


    public Disposable observeImportDB(){
        return view.importDatabase()
                .doOnNext(__->model.importDB())
                .subscribe(__->this.refresh());
    }
  public void refresh() {
      view.setData(model.getGameList(20));
  }
}
