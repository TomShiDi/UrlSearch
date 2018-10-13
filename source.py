import requests
from requests.exceptions import RequestException
from urllib.parse import urlencode
from bs4 import BeautifulSoup
from multiprocessing import Pool
#from functools import partial
#import lxml


def get_page_source(url):

    header = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0'
    }
    response = requests.get(url,headers=header)
    if response.status_code == 200:
        return response.text
    return None


def get_url_info(html):
    soup = BeautifulSoup(html,'lxml')
    hrefs = []
    h3s = soup.find_all('h3',class_='t')
    for h3 in h3s:
        # print(h3.find('a')['href'])
        hrefs.append(h3.find('a')['href'])
    # print(hrefs)
    return hrefs
    # print(h3s)


def main(index,keyword='inurl:asp'):
    data = {
        'wd': keyword,
        'pn': index,
        'oq': keyword
    }
    url = 'https://www.baidu.com/s?' + urlencode(data) +'&ie=utf-8&rsv_idx=1&rsv_pq=bbebc4a30001f9bc&rsv_t=61d0ehaa%2F20HbDmlS0RpzTBh5r8Gp7iKLJa0bVIgQYFsGyW7ikxk40aD3ZI'
    # print(get_page_source(url))
    hrefs = get_url_info(get_page_source(url))
    write_to_file(hrefs)
    # return hrefs


def write_to_file(hrefs):
    with open('urls.txt','a',encoding='utf-8') as f:
        for item in hrefs:
            f.write(str(item) + '\n')


if __name__ == '__main__':
    # partial_work = partial(main,keyword='图片')
    # argc_keyword = ['图片' for i in range(10)]
    # argc_index = [i*10 for i in range(10)]
    # func_var = zip(argc_keyword,argc_index)
    pool = Pool()
    pool.map(main,[i*10 for i in range(50)])