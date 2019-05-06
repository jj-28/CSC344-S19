import re
import sys
import subprocess
import os
import json
import pprint
import json2table


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
            makeJson(str(a_n), ln, s_name, r_file, a_fpath, rel_dir)
            a_n += 1
        # os.chdir(dir_name)
    # print(os.getcwd())
    #    tt =os.path.expanduser(dir_name)
    # r = "cd .."
    # subprocess.call(r)
    # subprocess.call(r)
    os.chdir("..")
    os.chdir("..")
    print(os.getcwd())
    for r, d, f in os.walk(os.getcwd()):
        for nn in f:
            # top level relative directory (Ex: ../C)
            rd = os.path.relpath(r, dir_name)
            # absolute file path for each file in directory
            ap = os.path.join(r, nn)
            #	    print(ap)
            # relative file path (../C/main.c)
            # os.chdir(r)
            #            if '.json' in ap and not 'meta' in ap:
            if '.json' in nn:
                with open(ap) as f_f:
                    jsoninfo = json.load(f_f)
                    bd = "LEFT_TO_RIGHT"
                    ta = {"style": "width:100"}
                    json_list[a_n] = json2table.convert(jsoninfo, build_direction=bd, table_attributes=ta)
            #		    os.chdir("..")
            #		    os.chdir("..")
            else:
                continue
    a_12 = 0
    os.chdir("..")
    with open("index.html", "w") as me:
        # for key, value, in dir_list.items():
        tag = "<body>"
        btag = "</body>"
        br = "<br>"
        # print(json_list.items() )
        for k, v, in json_list.items():
            # print(v)
            h = h + v + br
            a_12 += 1
            # print(h)
            # me.write(h)
        # h = tag + h + btag
        me.writelines(h)


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
                m = m + line + ''
            m = re.sub(re.compile("/\*.*?\*/", re.DOTALL), "", m)
            m = re.sub(re.compile("//.?\n", re.DOTALL), "", m)
            m = m.replace("\n", "")
            m = m.replace("\r", "")
            root['src'] = m
            json.dump(root, outfile, indent=4, sort_keys=True)


if __name__ == "__main__":
    getInfo()
