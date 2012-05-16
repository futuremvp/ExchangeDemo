//
//  NSStringTrimTests.m
//  NSStringTrimTests
//
//  Created by Cyril Wei on 5/15/12.
//  Copyright (c) 2012 ThoughtWorks. All rights reserved.
//

#import "NSStringTrimTests.h"
#import "NSString+Trim.h"

@implementation NSStringTrimTests

- (void)testExample
{
    NSString *stringWithSpace = @"  I have spaces    ";
    NSString *expected = @"I have spaces";
    
    STAssertEqualObjects(expected, [stringWithSpace trim], nil);
}

@end
