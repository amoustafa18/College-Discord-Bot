package camiscollegecorner;

import sx.blah.discord.handle.impl.obj.ReactionEmoji;

public abstract class Constants {
	public static final boolean DEBUG_MODE = false; //when true, the bot will ONLY be responsive in #bot-team channel

	public static final ReactionEmoji OK_EMOJI = ReactionEmoji.of("\u1F197");
	public static final ReactionEmoji CHICKEN_EGG_EMOJI = ReactionEmoji.of("\u1F95A");
	public static final ReactionEmoji HATCHING_EMOJI = ReactionEmoji.of("\u1F423");
	public static final ReactionEmoji HATCHED_EMOJI = ReactionEmoji.of("\u1F425");
	public static final ReactionEmoji BABY_CHICK_EMOJI = ReactionEmoji.of("\u1F424");
	public static final ReactionEmoji BIRD_EMOJI = ReactionEmoji.of("\u1F426");
	public static final ReactionEmoji WHITE_CIRCLE_EMOJI = ReactionEmoji.of("\u26AA");
	public static final ReactionEmoji BLUE_CIRCLE_EMOJI = ReactionEmoji.of("\u1F535");
	public static final ReactionEmoji RED_CIRCLE_EMOJI = ReactionEmoji.of("\u1F534");
	public static final ReactionEmoji EVERGREEN_EMOJI = ReactionEmoji.of("\u1F332");
	public static final ReactionEmoji AIRPLANE_EMOJI = ReactionEmoji.of("\u2708");
	public static final ReactionEmoji WARNING_EMOJI = ReactionEmoji.of("\u26A0");
	public static final ReactionEmoji STAR_EMOJI = ReactionEmoji.of("\u2B50");

	public static final int PIN_STAR_REACT_COUNT = 5;

	public static final long READ_RULES_MESSAGE_ID = 551168954714947605l;
	public static final long CHOOSE_ROLES_MESSAGE_ID = 551182441092481025l;

	public static final long VISIBLE_ROLE_ID = 551171218485805083l;
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

	public static final long CUTE_PICS_CHANNEL_ID = 550351556676026368l;
	public static final long GENERAL_CHANNEL_ID = 550348648001699840l;
	public static final long NSFW_GENERAL_CHANNEL_ID = 550348672613744641l;

	public static final String COMMAND_LIST = "src/main/resources/commands.txt";
	public static final String CMD_PREFIX = "!";
	public static final String PLAYING_TEXT = "Type " + CMD_PREFIX + "help for commands";
}