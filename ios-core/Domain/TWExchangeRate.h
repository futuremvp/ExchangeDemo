//
//  TWExchangeRate.h
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TWExchangeRate : NSObject

@property (nonatomic, retain) NSString *countryName;
@property (nonatomic, retain) NSString *currencyCode;
@property (nonatomic, assign) double exchangeRate;

@end
