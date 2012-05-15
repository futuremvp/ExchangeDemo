#!/usr/bin/python
import os
import os.path

CURRENT_ABSOLUTE_DIR = ''
AUTOMATED_TAG = '@automated'
AUTOMATION_TAG = '@automation'
SCENARIO_TAG = 'Scenario:'
SCENARIO_OUTLINE_TAG = 'Scenario Outline:'
all_automated = 0
all_scenario = 0

def listFiles(dir):
    base_dir = os.path.abspath(dir)
    subdirlist = []
    for item in os.listdir(dir):
        if os.path.isfile(os.path.join(base_dir,item)):
            doStatistic(os.path.join(base_dir,item))
        else:
            subdirlist.append(os.path.join(base_dir, item))
    for subdir in subdirlist:
        listFiles(subdir)

def doStatistic(file_name):
    global all_automated
    global all_scenario
    extension = os.path.splitext(file_name)[1]
    if extension == '.feature': 
        f = open(file_name)
        content = f.read()
        f.close()
        automated = content.count(AUTOMATED_TAG)
        automated += content.count(AUTOMATION_TAG)
        scenario =  content.count(SCENARIO_TAG)
        scenario +=  content.count(SCENARIO_OUTLINE_TAG)
        all_automated += automated 
        all_scenario += scenario
        print "", file_name.replace(CURRENT_ABSOLUTE_DIR, '', 1),":",automated,'/',scenario 


CURRENT_ABSOLUTE_DIR =  os.path.abspath('.') 
listFiles('.')
print '----------------------'
print 'All:', all_automated, '/', all_scenario
