// Scbd

// [配置项] 记分板轮播时间（秒）
global_switch_time = 300;

// [配置项] 初始化记分板信息
__on_start() -> (
	// 信息记分板
	// ensure_scoreboard(记分板ID, 统计类型, 显示名称, 颜色, 是否在订阅列表中忽略)
	ensure_scoreboard('scbd.mine', 'dummy', format('c 挖掘'), 'yellow');
	ensure_scoreboard('scbd.place', 'dummy', format('c 放置'), 'aqua');
	ensure_scoreboard('scbd.play', 'dummy', format('c 在线', 'w  (min)'), 'green');
	ensure_scoreboard('scbd.walk', 'dummy', format('c 行走', 'w  (km)'), 'dark_aqua');
	ensure_scoreboard('scbd.aviate', 'dummy', format('c 飞行', 'w  (km)'), 'light_purple');
	ensure_scoreboard('scbd.bedrock', 'dummy', format('c 破基岩'), 'dark_red');
	ensure_scoreboard('scbd.health', 'health', format('r ❤'), null, true);
	scoreboard_property('scbd.health', 'display_slot', 'list');
	scoreboard_property('scbd.health', 'render_type', 'hearts');
	ensure_scoreboard('scbd.deaths', 'deathCount', format('c 死亡'), 'red');
	ensure_scoreboard('scbd.trade', 'minecraft.custom:minecraft.traded_with_villager', format('c 交易'), 'dark_green');
	
	// 轮播记分板
	ensure_scoreboard('scbd.roll', 'dummy', format('c 轮播'), 'gold');

	// 默认记分板（帮助信息）
	ensure_scoreboard('scbd.blank', 'dummy', '', 'white', true);
	ensure_help_msg();
);

// 所有方块 ID，用以统计放置和破坏次数
global_blocks = ['acacia_button','acacia_door','acacia_fence','acacia_fence_gate','acacia_leaves','acacia_log','acacia_planks','acacia_pressure_plate','acacia_sapling','acacia_sign','acacia_slab','acacia_stairs','acacia_trapdoor','acacia_wall_sign','acacia_wood','activator_rail','air','allium','amethyst_block','amethyst_cluster','ancient_debris','andesite','andesite_slab','andesite_stairs','andesite_wall','anvil','attached_melon_stem','attached_pumpkin_stem','azalea','azalea_leaves','azure_bluet','bamboo','bamboo_sapling','barrel','barrier','basalt','beacon','bedrock','bee_nest','beehive','beetroots','bell','big_dripleaf','big_dripleaf_stem','birch_button','birch_door','birch_fence','birch_fence_gate','birch_leaves','birch_log','birch_planks','birch_pressure_plate','birch_sapling','birch_sign','birch_slab','birch_stairs','birch_trapdoor','birch_wall_sign','birch_wood','black_banner','black_bed','black_candle','black_candle_cake','black_carpet','black_concrete','black_concrete_powder','black_glazed_terracotta','black_shulker_box','black_stained_glass','black_stained_glass_pane','black_terracotta','black_wall_banner','black_wool','blackstone','blackstone_slab','blackstone_stairs','blackstone_wall','blast_furnace','blue_banner','blue_bed','blue_candle','blue_candle_cake','blue_carpet','blue_concrete','blue_concrete_powder','blue_glazed_terracotta','blue_ice','blue_orchid','blue_shulker_box','blue_stained_glass','blue_stained_glass_pane','blue_terracotta','blue_wall_banner','blue_wool','bone_block','bookshelf','brain_coral','brain_coral_block','brain_coral_fan','brain_coral_wall_fan','brewing_stand','brick_slab','brick_stairs','brick_wall','bricks','brown_banner','brown_bed','brown_candle','brown_candle_cake','brown_carpet','brown_concrete','brown_concrete_powder','brown_glazed_terracotta','brown_mushroom','brown_mushroom_block','brown_shulker_box','brown_stained_glass','brown_stained_glass_pane','brown_terracotta','brown_wall_banner','brown_wool','bubble_column','bubble_coral','bubble_coral_block','bubble_coral_fan','bubble_coral_wall_fan','budding_amethyst','cactus','cake','calcite','campfire','candle','candle_cake','carrots','cartography_table','carved_pumpkin','cauldron','cave_air','cave_vines','cave_vines_plant','chain','chain_command_block','chest','chipped_anvil','chiseled_deepslate','chiseled_nether_bricks','chiseled_polished_blackstone','chiseled_quartz_block','chiseled_red_sandstone','chiseled_sandstone','chiseled_stone_bricks','chorus_flower','chorus_plant','clay','coal_block','coal_ore','coarse_dirt','cobbled_deepslate','cobbled_deepslate_slab','cobbled_deepslate_stairs','cobbled_deepslate_wall','cobblestone','cobblestone_slab','cobblestone_stairs','cobblestone_wall','cobweb','cocoa','command_block','comparator','composter','conduit','copper_block','copper_ore','cornflower','cracked_deepslate_bricks','cracked_deepslate_tiles','cracked_nether_bricks','cracked_polished_blackstone_bricks','cracked_stone_bricks','crafting_table','creeper_head','creeper_wall_head','crimson_button','crimson_door','crimson_fence','crimson_fence_gate','crimson_fungus','crimson_hyphae','crimson_nylium','crimson_planks','crimson_pressure_plate','crimson_roots','crimson_sign','crimson_slab','crimson_stairs','crimson_stem','crimson_trapdoor','crimson_wall_sign','crying_obsidian','cut_copper','cut_copper_slab','cut_copper_stairs','cut_red_sandstone','cut_red_sandstone_slab','cut_sandstone','cut_sandstone_slab','cyan_banner','cyan_bed','cyan_candle','cyan_candle_cake','cyan_carpet','cyan_concrete','cyan_concrete_powder','cyan_glazed_terracotta','cyan_shulker_box','cyan_stained_glass','cyan_stained_glass_pane','cyan_terracotta','cyan_wall_banner','cyan_wool','damaged_anvil','dandelion','dark_oak_button','dark_oak_door','dark_oak_fence','dark_oak_fence_gate','dark_oak_leaves','dark_oak_log','dark_oak_planks','dark_oak_pressure_plate','dark_oak_sapling','dark_oak_sign','dark_oak_slab','dark_oak_stairs','dark_oak_trapdoor','dark_oak_wall_sign','dark_oak_wood','dark_prismarine','dark_prismarine_slab','dark_prismarine_stairs','daylight_detector','dead_brain_coral','dead_brain_coral_block','dead_brain_coral_fan','dead_brain_coral_wall_fan','dead_bubble_coral','dead_bubble_coral_block','dead_bubble_coral_fan','dead_bubble_coral_wall_fan','dead_bush','dead_fire_coral','dead_fire_coral_block','dead_fire_coral_fan','dead_fire_coral_wall_fan','dead_horn_coral','dead_horn_coral_block','dead_horn_coral_fan','dead_horn_coral_wall_fan','dead_tube_coral','dead_tube_coral_block','dead_tube_coral_fan','dead_tube_coral_wall_fan','deepslate','deepslate_brick_slab','deepslate_brick_stairs','deepslate_brick_wall','deepslate_bricks','deepslate_coal_ore','deepslate_copper_ore','deepslate_diamond_ore','deepslate_emerald_ore','deepslate_gold_ore','deepslate_iron_ore','deepslate_lapis_ore','deepslate_redstone_ore','deepslate_tile_slab','deepslate_tile_stairs','deepslate_tile_wall','deepslate_tiles','detector_rail','diamond_block','diamond_ore','diorite','diorite_slab','diorite_stairs','diorite_wall','dirt','dirt_path','dispenser','dragon_egg','dragon_head','dragon_wall_head','dried_kelp_block','dripstone_block','dropper','emerald_block','emerald_ore','enchanting_table','end_gateway','end_portal','end_portal_frame','end_rod','end_stone','end_stone_brick_slab','end_stone_brick_stairs','end_stone_brick_wall','end_stone_bricks','ender_chest','exposed_copper','exposed_cut_copper','exposed_cut_copper_slab','exposed_cut_copper_stairs','farmland','fern','fire','fire_coral','fire_coral_block','fire_coral_fan','fire_coral_wall_fan','fletching_table','flower_pot','flowering_azalea','flowering_azalea_leaves','frosted_ice','furnace','gilded_blackstone','glass','glass_pane','glow_lichen','glowstone','gold_block','gold_ore','granite','granite_slab','granite_stairs','granite_wall','grass','grass_block','gravel','gray_banner','gray_bed','gray_candle','gray_candle_cake','gray_carpet','gray_concrete','gray_concrete_powder','gray_glazed_terracotta','gray_shulker_box','gray_stained_glass','gray_stained_glass_pane','gray_terracotta','gray_wall_banner','gray_wool','green_banner','green_bed','green_candle','green_candle_cake','green_carpet','green_concrete','green_concrete_powder','green_glazed_terracotta','green_shulker_box','green_stained_glass','green_stained_glass_pane','green_terracotta','green_wall_banner','green_wool','grindstone','hanging_roots','hay_block','heavy_weighted_pressure_plate','honey_block','honeycomb_block','hopper','horn_coral','horn_coral_block','horn_coral_fan','horn_coral_wall_fan','ice','infested_chiseled_stone_bricks','infested_cobblestone','infested_cracked_stone_bricks','infested_deepslate','infested_mossy_stone_bricks','infested_stone','infested_stone_bricks','iron_bars','iron_block','iron_door','iron_ore','iron_trapdoor','jack_o_lantern','jigsaw','jukebox','jungle_button','jungle_door','jungle_fence','jungle_fence_gate','jungle_leaves','jungle_log','jungle_planks','jungle_pressure_plate','jungle_sapling','jungle_sign','jungle_slab','jungle_stairs','jungle_trapdoor','jungle_wall_sign','jungle_wood','kelp','kelp_plant','ladder','lantern','lapis_block','lapis_ore','large_amethyst_bud','large_fern','lava','lava_cauldron','lectern','lever','light','light_blue_banner','light_blue_bed','light_blue_candle','light_blue_candle_cake','light_blue_carpet','light_blue_concrete','light_blue_concrete_powder','light_blue_glazed_terracotta','light_blue_shulker_box','light_blue_stained_glass','light_blue_stained_glass_pane','light_blue_terracotta','light_blue_wall_banner','light_blue_wool','light_gray_banner','light_gray_bed','light_gray_candle','light_gray_candle_cake','light_gray_carpet','light_gray_concrete','light_gray_concrete_powder','light_gray_glazed_terracotta','light_gray_shulker_box','light_gray_stained_glass','light_gray_stained_glass_pane','light_gray_terracotta','light_gray_wall_banner','light_gray_wool','light_weighted_pressure_plate','lightning_rod','lilac','lily_of_the_valley','lily_pad','lime_banner','lime_bed','lime_candle','lime_candle_cake','lime_carpet','lime_concrete','lime_concrete_powder','lime_glazed_terracotta','lime_shulker_box','lime_stained_glass','lime_stained_glass_pane','lime_terracotta','lime_wall_banner','lime_wool','lodestone','loom','magenta_banner','magenta_bed','magenta_candle','magenta_candle_cake','magenta_carpet','magenta_concrete','magenta_concrete_powder','magenta_glazed_terracotta','magenta_shulker_box','magenta_stained_glass','magenta_stained_glass_pane','magenta_terracotta','magenta_wall_banner','magenta_wool','magma_block','medium_amethyst_bud','melon','melon_stem','moss_block','moss_carpet','mossy_cobblestone','mossy_cobblestone_slab','mossy_cobblestone_stairs','mossy_cobblestone_wall','mossy_stone_brick_slab','mossy_stone_brick_stairs','mossy_stone_brick_wall','mossy_stone_bricks','moving_piston','mushroom_stem','mycelium','nether_brick_fence','nether_brick_slab','nether_brick_stairs','nether_brick_wall','nether_bricks','nether_gold_ore','nether_portal','nether_quartz_ore','nether_sprouts','nether_wart','nether_wart_block','netherite_block','netherrack','note_block','oak_button','oak_door','oak_fence','oak_fence_gate','oak_leaves','oak_log','oak_planks','oak_pressure_plate','oak_sapling','oak_sign','oak_slab','oak_stairs','oak_trapdoor','oak_wall_sign','oak_wood','observer','obsidian','orange_banner','orange_bed','orange_candle','orange_candle_cake','orange_carpet','orange_concrete','orange_concrete_powder','orange_glazed_terracotta','orange_shulker_box','orange_stained_glass','orange_stained_glass_pane','orange_terracotta','orange_tulip','orange_wall_banner','orange_wool','oxeye_daisy','oxidized_copper','oxidized_cut_copper','oxidized_cut_copper_slab','oxidized_cut_copper_stairs','packed_ice','peony','petrified_oak_slab','pink_banner','pink_bed','pink_candle','pink_candle_cake','pink_carpet','pink_concrete','pink_concrete_powder','pink_glazed_terracotta','pink_shulker_box','pink_stained_glass','pink_stained_glass_pane','pink_terracotta','pink_tulip','pink_wall_banner','pink_wool','piston','piston_head','player_head','player_wall_head','podzol','pointed_dripstone','polished_andesite','polished_andesite_slab','polished_andesite_stairs','polished_basalt','polished_blackstone','polished_blackstone_brick_slab','polished_blackstone_brick_stairs','polished_blackstone_brick_wall','polished_blackstone_bricks','polished_blackstone_button','polished_blackstone_pressure_plate','polished_blackstone_slab','polished_blackstone_stairs','polished_blackstone_wall','polished_deepslate','polished_deepslate_slab','polished_deepslate_stairs','polished_deepslate_wall','polished_diorite','polished_diorite_slab','polished_diorite_stairs','polished_granite','polished_granite_slab','polished_granite_stairs','poppy','potatoes','potted_acacia_sapling','potted_allium','potted_azalea_bush','potted_azure_bluet','potted_bamboo','potted_birch_sapling','potted_blue_orchid','potted_brown_mushroom','potted_cactus','potted_cornflower','potted_crimson_fungus','potted_crimson_roots','potted_dandelion','potted_dark_oak_sapling','potted_dead_bush','potted_fern','potted_flowering_azalea_bush','potted_jungle_sapling','potted_lily_of_the_valley','potted_oak_sapling','potted_orange_tulip','potted_oxeye_daisy','potted_pink_tulip','potted_poppy','potted_red_mushroom','potted_red_tulip','potted_spruce_sapling','potted_warped_fungus','potted_warped_roots','potted_white_tulip','potted_wither_rose','powder_snow','powder_snow_cauldron','powered_rail','prismarine','prismarine_brick_slab','prismarine_brick_stairs','prismarine_bricks','prismarine_slab','prismarine_stairs','prismarine_wall','pumpkin','pumpkin_stem','purple_banner','purple_bed','purple_candle','purple_candle_cake','purple_carpet','purple_concrete','purple_concrete_powder','purple_glazed_terracotta','purple_shulker_box','purple_stained_glass','purple_stained_glass_pane','purple_terracotta','purple_wall_banner','purple_wool','purpur_block','purpur_pillar','purpur_slab','purpur_stairs','quartz_block','quartz_bricks','quartz_pillar','quartz_slab','quartz_stairs','rail','raw_copper_block','raw_gold_block','raw_iron_block','red_banner','red_bed','red_candle','red_candle_cake','red_carpet','red_concrete','red_concrete_powder','red_glazed_terracotta','red_mushroom','red_mushroom_block','red_nether_brick_slab','red_nether_brick_stairs','red_nether_brick_wall','red_nether_bricks','red_sand','red_sandstone','red_sandstone_slab','red_sandstone_stairs','red_sandstone_wall','red_shulker_box','red_stained_glass','red_stained_glass_pane','red_terracotta','red_tulip','red_wall_banner','red_wool','redstone_block','redstone_lamp','redstone_ore','redstone_torch','redstone_wall_torch','redstone_wire','repeater','repeating_command_block','respawn_anchor','rooted_dirt','rose_bush','sand','sandstone','sandstone_slab','sandstone_stairs','sandstone_wall','scaffolding','sculk_sensor','sea_lantern','sea_pickle','seagrass','shroomlight','shulker_box','skeleton_skull','skeleton_wall_skull','slime_block','small_amethyst_bud','small_dripleaf','smithing_table','smoker','smooth_basalt','smooth_quartz','smooth_quartz_slab','smooth_quartz_stairs','smooth_red_sandstone','smooth_red_sandstone_slab','smooth_red_sandstone_stairs','smooth_sandstone','smooth_sandstone_slab','smooth_sandstone_stairs','smooth_stone','smooth_stone_slab','snow','snow_block','soul_campfire','soul_fire','soul_lantern','soul_sand','soul_soil','soul_torch','soul_wall_torch','spawner','sponge','spore_blossom','spruce_button','spruce_door','spruce_fence','spruce_fence_gate','spruce_leaves','spruce_log','spruce_planks','spruce_pressure_plate','spruce_sapling','spruce_sign','spruce_slab','spruce_stairs','spruce_trapdoor','spruce_wall_sign','spruce_wood','sticky_piston','stone','stone_brick_slab','stone_brick_stairs','stone_brick_wall','stone_bricks','stone_button','stone_pressure_plate','stone_slab','stone_stairs','stonecutter','stripped_acacia_log','stripped_acacia_wood','stripped_birch_log','stripped_birch_wood','stripped_crimson_hyphae','stripped_crimson_stem','stripped_dark_oak_log','stripped_dark_oak_wood','stripped_jungle_log','stripped_jungle_wood','stripped_oak_log','stripped_oak_wood','stripped_spruce_log','stripped_spruce_wood','stripped_warped_hyphae','stripped_warped_stem','structure_block','structure_void','sugar_cane','sunflower','sweet_berry_bush','tall_grass','tall_seagrass','target','terracotta','tinted_glass','tnt','torch','trapped_chest','tripwire','tripwire_hook','tube_coral','tube_coral_block','tube_coral_fan','tube_coral_wall_fan','tuff','turtle_egg','twisting_vines','twisting_vines_plant','vine','void_air','wall_torch','warped_button','warped_door','warped_fence','warped_fence_gate','warped_fungus','warped_hyphae','warped_nylium','warped_planks','warped_pressure_plate','warped_roots','warped_sign','warped_slab','warped_stairs','warped_stem','warped_trapdoor','warped_wall_sign','warped_wart_block','water','water_cauldron','waxed_copper_block','waxed_cut_copper','waxed_cut_copper_slab','waxed_cut_copper_stairs','waxed_exposed_copper','waxed_exposed_cut_copper','waxed_exposed_cut_copper_slab','waxed_exposed_cut_copper_stairs','waxed_oxidized_copper','waxed_oxidized_cut_copper','waxed_oxidized_cut_copper_slab','waxed_oxidized_cut_copper_stairs','waxed_weathered_copper','waxed_weathered_cut_copper','waxed_weathered_cut_copper_slab','waxed_weathered_cut_copper_stairs','weathered_copper','weathered_cut_copper','weathered_cut_copper_slab','weathered_cut_copper_stairs','weeping_vines','weeping_vines_plant','wet_sponge','wheat','white_banner','white_bed','white_candle','white_candle_cake','white_carpet','white_concrete','white_concrete_powder','white_glazed_terracotta','white_shulker_box','white_stained_glass','white_stained_glass_pane','white_terracotta','white_tulip','white_wall_banner','white_wool','wither_rose','wither_skeleton_skull','wither_skeleton_wall_skull','yellow_banner','yellow_bed','yellow_candle','yellow_candle_cake','yellow_carpet','yellow_concrete','yellow_concrete_powder','yellow_glazed_terracotta','yellow_shulker_box','yellow_stained_glass','yellow_stained_glass_pane','yellow_terracotta','yellow_wall_banner','yellow_wool','zombie_head','zombie_wall_head'];

// 各维度玩家附近的基岩
global_bedrocks = {};

// 记分板列表
global_scoreboards = {};

// 当前轮播中的记分板
global_current = 1;

// 时间计数器
global_counter = 0;
global_switch_time = global_switch_time * 20;

// 轮播队伍颜色
global_roll_color = '';

__config() -> {
   'scope' -> 'global',
   'commands' -> {
		'' -> 'print_subscription_menu',
		'join <subscription>' -> 'subscribe'
	},
	'arguments' -> {
		'subscription' -> {
			'type' -> 'term',
			'suggester' -> _(args) -> (
				keys({'scbd.blank'} + global_scoreboards)
			),
		}
    }
};


// 创建 & 配置记分板
ensure_scoreboard(objective, criterion, display_name, color, ... exclude) -> (
	if(scoreboard(objective) == null, scoreboard_add(objective, criterion););
	scoreboard_property(objective, 'display_name', display_name);
	if(color,
		team_add(objective);
		team_property(objective, 'color', color);
		scoreboard_property(objective, 'display_slot', 'sidebar.team.'+color);
		if(objective == 'scbd.roll', 
			global_roll_color = color;
			scoreboard_property(keys(global_scoreboards):0, 'display_slot', 'sidebar.team.' + color);
		);
	);
	if(exclude != [true], put(global_scoreboards, objective, display_name));
);


// 分数 += 1
scoreboard_incr(objective, key, value) -> (
	scoreboard(objective, key, scoreboard(objective, key) + value);
);


// 默认记分板显示帮助
ensure_help_msg() -> (
	ensure_scoreboard('scbd.default', 'dummy', format('y 提示'), '', true);
	scoreboard('scbd.default', '使用§x§a/scbd§r§x', 1);
	scoreboard('scbd.default', '订阅记分板', 0);
	scoreboard_property('scbd.default', 'display_slot', 'sidebar');
);


update_all_scbds() -> (
	for(player('all'),
		update_scbds(_);
	);
);


update_scbds(player) -> (
	if (query(player, 'player_type') == 'fake',
		run('scoreboard players reset ' + player);
	,
		scoreboard('scbd.walk', player, round(statistic(player, 'custom', 'walk_one_cm')  / 100000));
		scoreboard('scbd.aviate', player, round(statistic(player, 'custom', 'aviate_one_cm')  / 100000));
		scoreboard('scbd.play', player, round(statistic(player, 'custom', 'play_time')  / 1200));
	);
);


print_subscription_menu() -> (
	msg = [];
	for(global_scoreboards,
		if(_ != 'scbd.roll',
			msg = [...msg, 'w [', 'y ' + global_scoreboards:_, '!/scbd join ' + _, 'w ] '];
		);
	);
	print(player(), format('w \n', 'wb 记分板列表', 'w  - 点击以订阅', 'w \n'));
	print(player(), format('w [', 'cb 轮播', '!/scbd join scbd.roll', 'w ]', 'w  [', 'cb 不显示记分板', '!/scbd join scbd.blank', 'w ]'));
	print(player(), format(...msg, 'w \n'));
);


subscribe(objective) -> (
	if(global_scoreboards:objective || objective == 'scbd.blank',
		team_leave(player());
		team_add(objective, player());
		display_title(player(), 'actionbar', format('w 成功订阅 ', 'yb ' + global_scoreboards:objective));
		run('execute as ' + player() + ' at ' + player() + ' run playsound minecraft:block.wooden_button.click_on voice @s ~ ~ ~');
	,
		print(player(), format('r 记分板不存在'));
		run('execute as ' + player() + ' at ' + player() + ' run playsound minecraft:block.anvil.land voice @s ~ ~ ~ 0.2');
	);
);


check_all_bedrock(player) -> (
	for(system_info('world_dimensions'),
		if (!global_bedrocks~_, global_bedrocks:_ = []);
		check_dimension_bedrock(_);
	);
	for(player('all'),
		check_player_bedrock(_);
	);
);


// 破基岩计数
check_dimension_bedrock(dim) -> (
	in_dimension(dim,
		bedrocks = global_bedrocks:dim;
		for(bedrocks,
			[x, y, z] = [_:0, _:1, _:2];
			if(block(x, y, z) == 'air',
				delete(bedrocks, bedrocks ~ _);
				player = entity_selector('@p[x=' + x + ',y=' + y + ',z=' + z + ']'):0;
				scoreboard_incr('scbd.bedrock', player, 1);
			);
		);
		global_bedrocks:dim = bedrocks;
	)
);


check_player_bedrock(e) -> (
	dim = query(e, 'dimension');
	bedrocks = [];
	in_dimension(dim,
		[x, y, z] = query(e, 'pos');
		[x, y, z] = [floor(x), floor(y), floor(z)];
		for(range(x-3, x+4),
			xx = _ ;
			for(range(y-3, y+4),
				yy = _ ;
				for(range(z-3, z+4),
					zz = _ ;
					if(block(xx, yy, zz) == 'bedrock',
						if(bedrocks~[xx, yy, zz] == null, put(bedrocks, null, [xx, yy, zz]));
					);
				);
			);
		);
		global_bedrocks:dim = bedrocks;
	);
);


__on_player_connects(player) -> (
	task_thread('sync', 'sync_history_data', player);
);


// 同步历史数据
sync_history_data(player) -> (
	if (query(player, 'player_type') != 'fake',
		if(!query(player, 'has_scoreboard_tag', 'scbd_synced'),
			mined = 0;
			used = 0;
			for(global_blocks,
				mined += statistic(player, 'mined', _);
				used += statistic(player, 'used', _);
			);
			scoreboard('scbd.mine', player, mined);
			scoreboard('scbd.place', player, used);
			scoreboard('scbd.trade', player, statistic(player, 'custom', 'traded_with_villager'));
			scoreboard('scbd.deaths', player, statistic(player, 'custom', 'deaths'));
			run('tag ' + player + ' add scbd_synced');
			print(player, format('[Scbd] ', 'e 成功同步历史数据'))
		);
	);
);


__on_player_breaks_block(player, block) -> (
	scoreboard_incr('scbd.mine', player, 1);
);


__on_player_places_block(player, item_tuple, hand, block) -> (
	if(block == 'piston' || block == 'sticky_piston',
		task_thread('check_bedrock', 'check_all_bedrock', player);
	);
	scoreboard_incr('scbd.place', player, 1);
);


__on_tick() -> (
	global_counter += 1;
	if(global_counter == global_switch_time,
		global_counter = 0;
		if(keys(global_scoreboards):global_current == 'scbd.roll', global_current += 1);
		// 轮播记分板
		run('scoreboard objectives setdisplay sidebar.team.' + global_roll_color + ' ' + keys(global_scoreboards):global_current);
		global_current += 1;
		if(global_current == length(global_scoreboards), global_current = 0);
	);

	update_all_scbds();
);
