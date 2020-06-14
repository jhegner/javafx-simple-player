package br.com.jhegner.smp.view.helper;

import java.io.File;

import br.com.jhegner.smp.domain.Arquivo;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerHelper {

	private MediaPlayer mp;

	private static MediaPlayerHelper instance;

	private Arquivo arquivo;

	private MediaPlayerHelper(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public void play() {

		if (null == mp) {

			mp = new MediaPlayer(new Media(new File(arquivo.getNomeCompleto()).toURI().toString()));
			mp.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					if (mpIstNotNull()) {
						mp.stop();
						mp.dispose();
						mp = null;
					}
				}
			});

		}
		mp.play();
	}

	public void stop() {
		if (mpIstNotNull()) {
			mp.stop();
			mp.dispose();
			mp = null;
		}
	}

	public void pause() {
		if (mpIstNotNull()) {
			mp.pause();
		}
	}
	
	public void muteOrRemoveMute() {
		Boolean bool = mp.isMute() ? Boolean.FALSE : Boolean.TRUE;
		if (mpIstNotNull()) {
			mp.setMute(bool);
		}
	}
	
	public boolean isMute() {
		return mp.isMute();
	}
	
	private boolean mpIstNotNull() {
		return (mp != null);
	}

	public static MediaPlayerHelper getInstance() {
		return instance;
	}

	public static MediaPlayerHelper getInstance(Arquivo arquivo) {
		if (instance == null) {
			instance = new MediaPlayerHelper(arquivo);
		}
		return instance;
	}
}