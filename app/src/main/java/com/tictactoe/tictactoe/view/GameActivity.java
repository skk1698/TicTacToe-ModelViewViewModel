package com.tictactoe.tictactoe.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.tictactoe.tictactoe.R;
import com.tictactoe.tictactoe.databinding.GameActivityBinding;
import com.tictactoe.tictactoe.model.Player;
import com.tictactoe.tictactoe.utilities.StringUtility;
import com.tictactoe.tictactoe.viewmodel.TicTacToeGameViewModel;

public class GameActivity extends AppCompatActivity {

    private static final String NO_WINNER = "No one (Match Draw)";
    private static final String DIALOG_BOX_TITLE = "Winner : ";
    private TicTacToeGameViewModel ticTacToeGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        initDataBinding();
    }

    private void initDataBinding() {
        GameActivityBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.game_activity);
        ticTacToeGameViewModel = ViewModelProviders.of(this).get(TicTacToeGameViewModel.class);
        ticTacToeGameViewModel.init();
        activityGameBinding.setTicTacToeGameViewModel(ticTacToeGameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        ticTacToeGameViewModel.getGameWinner().observe(this, this::onGameWinnerChanged);
    }

    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || StringUtility.isNullOrEmpty(winner.name) ? NO_WINNER : winner.name;
        showDialog(winnerName);
    }


    private void showDialog(String winnerName) {
        new AlertDialog.Builder(this)
                .setTitle(DIALOG_BOX_TITLE + winnerName)
                .setPositiveButton(R.string.alert_dialog_restart_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initDataBinding();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
