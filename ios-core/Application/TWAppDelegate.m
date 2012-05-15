//
//  TWAppDelegate.m
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import "TWAppDelegate.h"

@implementation TWAppDelegate

@synthesize window = _window;

- (void)dealloc
{
    [_window release];
    [super dealloc];
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.window = [[[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]] autorelease];
    
    TWMainViewController *mainViewController = [[TWMainViewController alloc] init];
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:mainViewController];
    [mainViewController release];
    
    self.window.rootViewController = navigationController;
    [navigationController release];
    
    [self.window makeKeyAndVisible];
    return YES;
}

@end
