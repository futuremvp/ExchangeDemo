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

- (void)test_should_trim_spaces
{
    NSString *stringWithSpace = @"  I have spaces    ";
    NSString *expected = @"I have spaces ";
    
    STAssertEqualObjects(expected, [stringWithSpace trim], nil);
}

- (void)test_should_trim_new_lines
{
    NSString *stringWithSpace = @"  I have new lines  \n ";
    NSString *expected = @"I have new lines ";
    
    STAssertEqualObjects(expected, [stringWithSpace trim], nil);
}

@end
