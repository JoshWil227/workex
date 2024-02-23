<?php
/**
 * The header for our theme
 *
 * This is the template that displays all of the <head> section and everything up until <div id="content">
 *
 * @link https://developer.wordpress.org/themes/basics/template-files/#template-partials
 *
 * @package carbits
 */

?>
<!doctype html>
<html <?php language_attributes(); ?>>
<head>
	<meta charset="<?php bloginfo( 'charset' ); ?>">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="profile" href="https://gmpg.org/xfn/11">

	<?php wp_head(); ?>
</head>

<body <?php body_class(); ?>>
<?php wp_body_open(); ?>
<div id="page" class="site">
	<a class="skip-link screen-reader-text" href="#primary"><?php esc_html_e( 'Skip to content', 'carbits' ); ?></a>

	<header id="masthead" class="site-header">
		<div class="site-branding">
			<?php
			the_custom_logo();
			if ( is_front_page() && is_home() ) : //if home page is not a static page
				?>
				<div class="header-content-wrapper">
					<h1 class="site-title"><a class="site-link" href="<?php echo esc_url( home_url( '/' ) ); ?>" rel="home"><?php bloginfo( 'name' ); ?></a></h1>
					<nav id="site-navigation" class="main-navigation">
					<button class="menu-toggle" aria-controls="primary-menu" aria-expanded="false"><?php esc_html_e( 'Primary Menu', 'carbits' ); ?></button>
					<?php
					wp_nav_menu(
						array(
							'theme_location' => 'menu-1',
							'menu_id'        => 'primary-menu',
						)
					);
					?>
				</div>
				<?php
			else : //if home page is static page
				?>
				<div class="header-content-wrapper">
					<div class=logo-container>
						<h1 class="site-title"><a class="site-link" href="<?php echo esc_url( home_url( '/' ) ); ?>" rel="home"><?php bloginfo( 'name' ); ?></a></h1>
					</div>
					<div class="center-main-menu">
						<nav id="site-navigation" class="main-navigation">
                        <div class="menu-menu-1-container">
							<ul id="primary-menu" class="pmenu">
								<li id="menu-item-54" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-home current-menu-item page_item page-item-9 current_page_item menu-item-54"><a href="/home" aria-current="page">Home</a></li>
								<li id="menu-item-55" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-55"><a href="/blog">Posts</a></li>
							</ul>
						</div>                                     
					</div>
				</div>
				<?php
			endif;
			$carbits_description = get_bloginfo( 'description', 'display' );
			if ( $carbits_description || is_customize_preview() ) :
				?>
				<p class="site-description"><?php echo $carbits_description; // phpcs:ignore WordPress.Security.EscapeOutput.OutputNotEscaped ?></p>
			<?php endif; ?>
		</div><!-- .site-branding -->
		</nav><!-- #site-navigation -->
	</header><!-- #masthead -->
