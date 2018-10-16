package sportsapp.sashi.in.sportsapp.providers;

import com.activeandroid.Configuration;
import com.activeandroid.content.ContentProvider;

import sportsapp.sashi.in.sportsapp.models.app.AllPlayers;
import sportsapp.sashi.in.sportsapp.models.app.Batch;
import sportsapp.sashi.in.sportsapp.models.app.Coach;
import sportsapp.sashi.in.sportsapp.models.app.PlayerSession;
import sportsapp.sashi.in.sportsapp.models.app.Sessions;

public class DBProvider extends ContentProvider {

    @Override
    protected Configuration getConfiguration() {
        Configuration.Builder builder = new Configuration.Builder(getContext());
        builder.addModelClass(Coach.class);
        builder.addModelClass(Batch.class);
        builder.addModelClass(Sessions.class);
        builder.addModelClass(AllPlayers.class);
        builder.addModelClass(PlayerSession.class);
        return builder.create();
    }
}
