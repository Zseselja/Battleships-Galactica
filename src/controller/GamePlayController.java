package controller;

import model.GamePlayModel;
import view.GamePlayView;

public class GamePlayController {
	private GamePlayModel model;
	private GamePlayView view;

	public GamePlayController(GamePlayModel m, GamePlayView v) {
		this.model = m;
		this.view = v;
	}
}
