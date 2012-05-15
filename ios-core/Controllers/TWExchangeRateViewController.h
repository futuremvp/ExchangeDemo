//
//  TWExchangeRateViewController.h
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TWExchangeRateViewController : UITableViewController {
    NSMutableArray *exchangeRateList;
    
    NSMutableData *xmlData;
	NSURLConnection *urlConnection;
}

@end
