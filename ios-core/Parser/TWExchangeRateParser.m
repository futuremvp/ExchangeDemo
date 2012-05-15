//
//  TWExchangeRateParser.m
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import "TWExchangeRateParser.h"
#import "NSString+Trim.h"

@implementation TWExchangeRateParser

- (NSArray *)parseData:(NSData *)xmlData
{
	if (data == nil) {
		data = [[NSMutableArray alloc] init];
	}
	[data removeAllObjects];
	
	NSXMLParser *parser = [[NSXMLParser alloc] initWithData:xmlData];
	
	[parser setDelegate:self];
	[parser parse];
	
	[parser release];
	
	[data autorelease];
	return data;
}

#pragma mark -
#pragma mark XML Parser Delegate

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict
{
	if ([elementName isEqual:EXCHANGE_RATE]) {
		exchangeRateStarted = YES;
		exchangeRate = [[TWExchangeRate alloc] init];
	}
	else if (exchangeRateStarted) {
		tempString = [[NSMutableString alloc] init];
	}
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
	[tempString appendString:string];
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
	if ([elementName isEqual:EXCHANGE_RATE]) {
		[data addObject:exchangeRate];
		[exchangeRate release];
		exchangeRate = nil;
		exchangeRateStarted = NO;
	}
	else {
		if ([elementName isEqual:COUNTRY_NAME]) {
			exchangeRate.countryName = [tempString trim];
		}
		else if ([elementName isEqual:CURRENCY_CODE]) {
			exchangeRate.currencyCode = [tempString trim];
		}
		else if ([elementName isEqual:SELL_NOTES_AT_RATE]) {
			exchangeRate.exchangeRate = [tempString doubleValue];
		}
		[tempString release];
		tempString = nil;
	}
}

@end
