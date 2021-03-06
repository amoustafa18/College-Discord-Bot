package camiscollegecorner;

import sx.blah.discord.handle.impl.obj.ReactionEmoji;

public abstract class Constants {
	public static final boolean DEBUG_MODE = false; //when true, the bot will ONLY be responsive in #bot-team channel

	public static final ReactionEmoji OK_EMOJI = ReactionEmoji.of("\uD83C\uDD97");
	public static final ReactionEmoji CHICKEN_EGG_EMOJI = ReactionEmoji.of("\uD83D\uDC23");
	public static final ReactionEmoji HATCHING_EMOJI = ReactionEmoji.of("\uD83D\uDC23");
	public static final ReactionEmoji HATCHED_EMOJI = ReactionEmoji.of("\uD83D\uDC25");
	public static final ReactionEmoji BABY_CHICK_EMOJI = ReactionEmoji.of("\uD83D\uDC24");
	public static final ReactionEmoji BIRD_EMOJI = ReactionEmoji.of("\uD83D\uDC26");
	public static final ReactionEmoji WHITE_CIRCLE_EMOJI = ReactionEmoji.of("⚪");
	public static final ReactionEmoji BLUE_CIRCLE_EMOJI = ReactionEmoji.of("\uD83D\uDD35");
	public static final ReactionEmoji RED_CIRCLE_EMOJI = ReactionEmoji.of("\uD83D\uDD34");
	public static final ReactionEmoji EVERGREEN_EMOJI = ReactionEmoji.of("\uD83C\uDF32");
	public static final ReactionEmoji AIRPLANE_EMOJI = ReactionEmoji.of("✈");
	public static final ReactionEmoji WARNING_EMOJI = ReactionEmoji.of("⚠");
	public static final ReactionEmoji STAR_EMOJI = ReactionEmoji.of("⭐");
	public static final ReactionEmoji CHICKEN_EMOJI = ReactionEmoji.of("\uD83D\uDC14");
	public static final ReactionEmoji HEART_DECORATION_EMOJI = ReactionEmoji.of("\uD83D\uDC9F");
	public static final ReactionEmoji WHITE_CHECK_EMOJI  = ReactionEmoji.of("✅");
	public static final ReactionEmoji RED_X_EMOJI = ReactionEmoji.of("❌");

	public static final int PIN_STAR_REACT_COUNT = 7;

	public static final long READ_RULES_MESSAGE_ID = 551168954714947605l;
	public static final long CHOOSE_ROLES_MESSAGE_ID = 551182441092481025l;

	public static final long MEMBER_ROLE_ID = 551171218485805083l;
	public static final long FRESHMAN_ROLE_ID = 550353511699841034l;
	public static final long SOPHOMORE_ROLE_ID = 550353490086723584l;
	public static final long JUNIOR_ROLE_ID = 550353218337636414l;
	public static final long SENIOR_ROLE_ID = 550353223920254980l;
	public static final long UNDERGRAD_ROLE_ID = 550353186871836673l;
	public static final long THEY_ROLE_ID = 551167505918590989l;
	public static final long SHE_ROLE_ID = 551167419050360833l;
	public static final long HE_ROLE_ID = 551167286673801237l;
	public static final long TRANSFER_ROLE_ID = 550377261044334592l;
	public static final long INTERNATIONAL_ROLE_ID = 551066986784751626l;
	public static final long NSFW_ROLE_ID = 550381039697133588l;
	public static final long ALUM_ROLE_ID = 551513639593705492l;
	public static final long GAP_YEAR_ROLE_ID = 551446158912782346l;
	public static final long ADMIN_ROLE_ID = 550353118664196106l;
	public static final long BOT_TEAM_ROLE_ID = 551168467538018315l;

	//general, nsfw general, memes, nsfw memes, happy-boi-hours, recommendations, cute pets, gaming, bot-channel, vc-text
	public static final long CUTE_PICS_CHANNEL_ID = 550351556676026368l;
	public static final long GENERAL_CHANNEL_ID = 550348648001699840l;
	public static final long NSFW_GENERAL_CHANNEL_ID = 550348672613744641l;
	public static final long VIDEOS_AND_MEMES_CHANNEL_ID = 550348902352814090l;
	public static final long MEMES_CHANNEL_ID = 550348902352814090l;
	public static final long NSFW_MEMES_CHANNEL_ID = 550545157498470403l;
	public static final long BOT_CHANNEL_ID = 550359233267367956l;
	public static final long BOT_TEAM_CHANNEL_ID = 550872387802103809l;
	public static final long HAPPY_BOI_CHANNEL_ID = 552166060816728065l;
	public static final long RECOMMENDATIONS_CHANNEL_ID = 550836693083619349l;
	public static final long GAMING_CHANNEL_ID = 550826626204827649l;
	public static final long VC_TEXT_CHANNEL_ID = 550890457736019969l;
	public static final long ALL_CHANNELS_FLAG_ID = -42;

	public static final long[] ALL_CHANNELS_FLAG = new long[] {
			ALL_CHANNELS_FLAG_ID
	};

	public static final long[] CAT_CHANNELS = new long[] {
		CUTE_PICS_CHANNEL_ID,
		BOT_CHANNEL_ID
	};

	public static final long[] PRESTON_CHANNELS = new long[] {
			CUTE_PICS_CHANNEL_ID,
			BOT_CHANNEL_ID
	};

	public static final long[] MINIGAME_CHANNELS = new long[] {
		GENERAL_CHANNEL_ID,
		NSFW_GENERAL_CHANNEL_ID,
		MEMES_CHANNEL_ID,
		NSFW_MEMES_CHANNEL_ID,
		HAPPY_BOI_CHANNEL_ID,
		RECOMMENDATIONS_CHANNEL_ID,
		CUTE_PICS_CHANNEL_ID,
		GAMING_CHANNEL_ID,
		BOT_CHANNEL_ID,
		VC_TEXT_CHANNEL_ID,
		BOT_TEAM_CHANNEL_ID
	};

	public static final long WAFFLE_USER_ID = 180854214308790272l;
	public static final long STANA_USER_ID = 163998218965876737l;
	public static final long CAMI_USER_ID = 215333525795110913l;


	public static final String COMMAND_LIST = "src/main/resources/commands.txt";
	public static final String PRESTON_LIST = "src/main/resources/preston.txt";

	public static final String HANGMAN_IMAGE_0 = "https://cdn.discordapp.com/attachments/550872387802103809/553056240864067624/0.png";
	public static final String HANGMAN_IMAGE_1 = "https://cdn.discordapp.com/attachments/550872387802103809/553056264536457217/1.png";
	public static final String HANGMAN_IMAGE_2 = "https://cdn.discordapp.com/attachments/550872387802103809/553056284107210752/2.png";
	public static final String HANGMAN_IMAGE_3 = "https://cdn.discordapp.com/attachments/550872387802103809/553056309659041794/3.png";
	public static final String HANGMAN_IMAGE_4 = "https://cdn.discordapp.com/attachments/550872387802103809/553056329183526912/4.png";
	public static final String HANGMAN_IMAGE_5 = "https://cdn.discordapp.com/attachments/550872387802103809/553056351950077972/5.png";
	public static final String HANGMAN_IMAGE_6 = "https://cdn.discordapp.com/attachments/550872387802103809/553056380404105216/6.png";
	public static final String HANGMAN_IMAGE_7 = "https://cdn.discordapp.com/attachments/550872387802103809/553056403477102593/7.png";

	public static final String CMD_PREFIX = "c!";
	public static final String PLAYING_TEXT = "Type " + CMD_PREFIX + "help for commands";
}