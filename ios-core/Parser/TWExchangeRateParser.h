//
//  TWExchangeRateParser.h
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TWExchangeRate.h"

#define EXCHANGE_RATE @"ExchangeRate"
#define COUNTRY_NAME @"CountryName"
#define CURRENCY_CODE @"CurrencyCode"
#define SELL_NOTES_AT_RATE @"SellNotesAtRate"

@interface TWExchangeRateParser : NSObject <NSXMLParserDelegate> {
	NSMutableArray *data;
	NSMutableString *tempString;
	
	TWExchangeRate *exchangeRate;
	bool exchangeRateStarted;
}

- (NSArray *)parseData:(NSData *)xmlData;

@end
