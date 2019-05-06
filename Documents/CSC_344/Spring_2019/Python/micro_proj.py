import sys
import subprocess
import os


# instructions:
# Provide the directory name for the as an argument.
# Prepend "~/" to indicate a relative path


def wc():
    dir_name = sys.argv[1]
    print(dir_name)
    os.chdir(dir_name)
    for f in os.listdir(dir_name):
        np = os.path.join(dir_name, f)
        if os.path.isdir(np):
            # print(f)
            continue
        else:
            z = ['wc', '-l', f]
            subprocess.Popen(z)


if __name__ == "__main__":
    wc()
