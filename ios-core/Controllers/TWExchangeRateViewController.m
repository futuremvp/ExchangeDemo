//
//  TWExchangeRateViewController.m
//  ExchangeDemo
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import "TWExchangeRateViewController.h"
#import "TWExchangeRateParser.h"

@interface TWExchangeRateViewController ()
- (void)loadData;
@end

@implementation TWExchangeRateViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"Exchange Rates";
    }
    return self;
}

# pragma mark -
# pragma mark View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
	exchangeRateList = [[NSMutableArray alloc] init];
	
	[self loadData];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (void)loadData
{
	if ([exchangeRateList count] > 0) {
		return;
	}
	
	[[self tableView] reloadData];
	
	NSURL *url = [NSURL URLWithString:@"http://info.suncorp.com.au/bank/Datastreams/CurrencyService.ashx"];
	
	NSURLRequest *request = [NSURLRequest requestWithURL:url 
											 cachePolicy:NSURLRequestReloadIgnoringCacheData 
										 timeoutInterval:30];
	if (urlConnection) {
		[urlConnection cancel];
		[urlConnection release];
	}
	
	[xmlData release];
	xmlData = [[NSMutableData alloc] init];
	
	urlConnection = [[NSURLConnection alloc] initWithRequest:request delegate:self startImmediately:YES];
}

#pragma mark -
#pragma mark Connection Delegate
- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
	[xmlData appendData:data];
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection
{
	[exchangeRateList removeAllObjects];
	
	TWExchangeRateParser *parser = [[TWExchangeRateParser alloc] init];
	[exchangeRateList addObjectsFromArray:[parser parseData:xmlData]];
	
	[parser release];
	parser = nil;
	[urlConnection release];
	urlConnection = nil;
	[xmlData release];
	xmlData = nil;
	
	[[self tableView] reloadData];
}

#pragma mark - 
#pragma mark Table view data source

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [exchangeRateList count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
	static NSString *cellIdentifier = @"ExchangeRateCell";
	
	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
	if (cell == nil) {
		cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:cellIdentifier];
	}
	
	TWExchangeRate *exchangeRate = [exchangeRateList objectAtIndex:[indexPath row]];
	cell.textLabel.text = [NSString stringWithFormat:@"%@ (%@)", exchangeRate.countryName, exchangeRate.currencyCode];
	cell.detailTextLabel.text = [NSString stringWithFormat:@"%.4f", exchangeRate.exchangeRate];
		
	return cell;
}

@end
