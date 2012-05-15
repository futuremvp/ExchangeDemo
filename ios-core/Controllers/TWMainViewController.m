//
//  TWMainViewController.m
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import "TWMainViewController.h"
#import "TWExchangeRateViewController.h"

@interface TWMainViewController ()
- (void)startButtonPressed;
@end

@implementation TWMainViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"Start";
    }
    return self;
}

- (void)loadView
{
    UIView *view = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    view.backgroundColor = [UIColor lightGrayColor];
    
    self.view = view;
    [view release];
    
    UIButton *startButton = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    [startButton setTitle:@"Start" forState:UIControlStateNormal];
    startButton.frame = CGRectMake(44.0f, 152.0f, 232.0f, 44.0f);
    [startButton addTarget:self action:@selector(startButtonPressed) forControlEvents:UIControlEventTouchUpInside];
    
    [self.view addSubview:startButton];
    [startButton release];
}

- (void)startButtonPressed
{
    TWExchangeRateViewController *exchangeRateViewController = [[TWExchangeRateViewController alloc] init];
    [self.navigationController pushViewController:exchangeRateViewController animated:YES];
    [exchangeRateViewController release];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
