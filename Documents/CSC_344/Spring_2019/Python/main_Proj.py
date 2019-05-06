import re
import sys
import subprocess
import os
import json
from yattag import Doc
from os.path import dirname
import pprint


# import json2table

r_fileList = {}
def getInfo():
    # get the name of the directory you want to get as a
    dir_name = sys.argv[1]
    #    print(dir_name)
    os.chdir(dir_name)
    # empty list of directories
    dir_list = {}
    # empty json list, will hold info
    json_list = {}

    # counter for sub-directories
    a_n = 0
    h = ''
    # loop for the directories and files in the current working directory
    for root, dirs, files in os.walk(os.getcwd()):
        for name in files:
            # top level relative directory (Ex: ../C)
            rel_dir = os.path.relpath(root, dir_name)

            # absolute file path for each file in directory
            a_fpath = os.path.join(root, name)

            # relative file path (../C/main.c)
            r_file = os.path.join(rel_dir, name)
            r_file = r_file.replace("../", "")
            r_file = "../CSC344/" + r_file
            # change directory to root
            os.chdir(root)
            # use word count
            z = ['wc', '-l', name]
            # open up a count
            s = subprocess.Popen(z, stdout=subprocess.PIPE)
            str_1 = s.stdout.read()
            ln = str_1.partition(' ')[0]
            s_name = str_1.split(' ')[1]
            dir_list[a_n] = rel_dir
            # r_fileList[a_n] = r_file
            # makeJson(str(a_n), ln, s_name, r_file, a_fpath, rel_dir)
            makeHtml(str(a_n), ln, s_name, r_file, a_fpath, rel_dir)
            a_n += 1
    # os.chdir("..")
    # os.chdir("..")
    a_12 = 0
    os.chdir("..")
    with open("index.html", "w") as me:
        doc, tag, text = Doc().tagtext()
        doc.asis('<!DOCTYPE html>')
        with tag('html'):
            text()
            with tag('head'):
                text()
                with tag('style'):
                    text('table {font-family: arial, sans-serif; border-collapse: collapse;width: 100%;}td, '
                         'th {border: 1px solid #dddddd;text-align: left;padding: 8px;}tr:nth-child(even) {'
                         'background-color: #dddddd;}')
            with tag('body'):
                text()
                with tag('table style ="width:100%"'):
                    text()
                    with tag('tr'):
                        with tag('th'):
                            text("HTML Files")
                    with tag('tr'):
                        # print("name:  " + dir_name)
                        for rot, dirs, files in os.walk(os.getcwd()):
                            # print(rot)
                            for f_w in files:
                                rd = os.path.relpath(rot, dir_name)
                                # print("rd" + rd)
                                rf = os.path.join(rd, f_w)
                                rf =rf.replace("../", "")
                                rf = "../CSC344/" +rf
                                # print("rel file" + rf)
                                if ".html" in f_w:
                                    with tag('td'):
                                        with tag('a', ('href', rf)):
                                            text(rf)
        me.write(doc.getvalue())
    os.chdir("..")
    email = raw_input('Enter email: ')
    print("curr dir: " + os.getcwd())
    gh = 'zip -r csc.zip CSC344'
    os.system(gh)
    cmd ='./uuencode csc.zip "cscpFINAL.zip" | mailx -s "MY CODE" ' + email
    os.system(cmd)
    print("sent!")
    # open up a count




def makeJson(a_n, line_num, f_name, rel_path, a_path, rel_dir):
    root = {}
    rel_path = rel_path.replace("\r", "")
    rel_path = rel_path.replace("\n", "")
    f_name = f_name.replace("\r", "")
    f_name = f_name.replace("\n", "")
    j = "summary_" + a_n + ".json"
    m = ''

    with open(a_path, "r") as f:
        with open(j, "w") as outfile:
            root['filename'] = f_name
            root['path'] = rel_path
            root['num_of_lines'] = line_num
            for line in f:
                # m = m + line + '' + "\n"
                m = m + line + "\n"
                # m = m.replace("\n", "")
                m = m.replace("\r", "")
                pattern = re.compile("^(?!\#|\\.|\/|\}|\*).*(?!\").$", re.M | re.X)
                res = pattern.findall(m)
            # m = re.sub(re.compile("/\*.*?\*/", re.DOTALL), "", m)
            # m = re.sub(re.compile("^(?!\#|\\.|\/|\}|\*).*(?!\").$", re.DOTALL), "", m)
            # m = re.sub(re.compile("//.?\n", re.DOTALL), "", m)
            m = res
            # replace carriage returns n such


            root['src'] = m
            json.dump(root, outfile, indent=4, sort_keys=True)


def makeHtml(a_n, line_num, f_name, rel_path, a_path, rel_dir):
    rel_path = rel_path.replace("\r", "")
    rel_path = rel_path.replace("\n", "")
    rel_path = "../" + rel_path
    f_name = f_name.replace("\r", "")
    f_name = f_name.replace("\n", "")
    j = "summary_" + a_n + ".html"
    m = []
    with open(a_path, "r") as f:
        with open(j, "w") as outfile:
            doc, tag, text = Doc().tagtext()
            doc.asis('<!DOCTYPE html>')
            with tag('html'):
                text()
                with tag('head'):
                    text()
                    with tag('style'):
                        text('table {font-family: arial, sans-serif; border-collapse: collapse;width: 100%;}td, '
                             'th {border: 1px solid #dddddd;text-align: left;padding: 8px;}tr:nth-child(even) {'
                             'background-color: #dddddd;}')
                with tag('body'):
                    text()
                    with tag('table style ="width:100%"'):
                        text()
                        with tag('tr'):
                            with tag('th'):
                                text("File Name")
                            with tag('th'):
                                text("Path")
                            with tag('th'):
                                text("Line Numbers")
                            with tag('th'):
                                text("Identifiers")
                        with tag('tr'):
                            with tag('td'):
                                text(f_name)
                            with tag('td'):
                                with tag('a', ('href', rel_path)):
                                    text(rel_path)

                            with tag('td'):
                                text(line_num)
                        for line in f:
                            # m = m.append(line)
                            line = line.replace("\r", "")
                            line = line.replace("->", "")
                            line.strip()
                            with tag('tr'):
                                text(" ")
                                with tag('td'):
                                    text(" ")
                                with tag('td'):
                                    text(" ")
                                with tag('td'):
                                    text(" ")
                                with tag('td'):
                                    pattern = re.compile("^(?!\#|\\.|\/|\}|\*).*(?!\").$", re.M | re.X)

                                    res = pattern.findall(line)
                                    # m.append(res)
                                    str1 = ''.join(res)
                                    text(str1)
                                    # text(line)
            outfile.write(doc.getvalue())


if __name__ == "__main__":
    getInfo()
