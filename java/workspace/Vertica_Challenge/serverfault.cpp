#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>

using namespace std;

// Represents an entry in users.xml
// Some fields omitted due to laziness
struct User {
    string Id;
    string DisplayName;
};

// Represents an entry in posts.xml
// Some fields omitted due to laziness
struct Post {
    string Id;
    string PostTypeId;
    string OwnerUserId;
    string AcceptedAnswerId;
};


string parseFieldFromLine(const string &line, const string &key) {
    // We're looking for a thing that looks like:
    // [key]="[value]"
    // as part of a larger string.
    // We are given [key], and want to return [value].

    // Find the start of the pattern
    string keyPattern = key + "=\"";
    ssize_t idx = line.find(keyPattern);

    // No match
    if (idx == -1) return "";

    // Find the closing quote at the end of the pattern
    size_t start = idx + keyPattern.size();

    size_t end = start;
    while (line[end] != '"') {
        end++;
    }

    // Extract [value] from the overall string and return it
    // We have (start, end); substr() requires,
    // so we must compute, (start, length).
    return line.substr(start, end-start);
}

// Keep track of all users
map<string, User> users;

void readUsers(const string &filename) {
    ifstream fin;
    fin.open(filename.c_str());

    string line;
    while (getline(fin, line)) {
        User u;
        u.Id = parseFieldFromLine(line, "Id");
        u.DisplayName = parseFieldFromLine(line, "DisplayName");
        users[u.Id] = u;
    }
}

// Keep track of all posts
map<string, Post> posts;

void readPosts(const string &filename) {
    ifstream fin;
    fin.open(filename.c_str());

    string line;
    while (getline(fin, line)) {
        Post p;
        p.Id = parseFieldFromLine(line, "Id");
        p.PostTypeId = parseFieldFromLine(line, "PostTypeId");
        p.OwnerUserId = parseFieldFromLine(line, "OwnerUserId");
        p.AcceptedAnswerId = parseFieldFromLine(line, "AcceptedAnswerId");
        posts[p.Id] = p;
    }
}

User findUser(const string &Id) {
    if (users.find(Id) != users.end()) {
        return users[Id];
    }
    return User();
}

// Some data for the map
struct MapData {
    string DisplayName;
    int Count;
};

int main(int argv, char** argc) {
    // Load our data
    readUsers("users-short.xml");
    readPosts("posts-short.xml");

    // Calculate the users with the most answers
    map<string, MapData> answers;

    for (map<string, Post>::iterator ite = posts.begin(); ite != posts.end(); ite++) {
        Post p = ite->second;
        User u_p = findUser(p.OwnerUserId);
        answers[u_p.Id].DisplayName = u_p.DisplayName;
        if (p.PostTypeId == "2") { answers[u_p.Id].Count ++; }
    }

    cout << "Top 10 users with the most answers:" <<endl;
    for (int i = 0; i < 10; i++) {
        string key;
        MapData max_data = { "", 0 };
        for (map<string, MapData>::iterator it = answers.begin();
             it != answers.end(); ++it) {
            if (it->second.Count >= max_data.Count) {
                key = it->first;
                max_data = it->second;
            }
        }
        
        answers.erase(key);
        cout << max_data.Count << '\t' << max_data.DisplayName <<endl;
    }

    cout <<endl;

    // Calculate the users with the most accepted answers
    map<string, MapData> acceptedAnswers;

    for (map<string, Post>::iterator ite = posts.begin(); ite != posts.end(); ite++) {
        Post answerPost = ite->second;
        if (answerPost.PostTypeId == "2") {
            for (map<string, Post>::iterator ite2 = posts.begin(); ite2 != posts.end(); ite2++) {
                Post post = ite2->second;
                if (post.PostTypeId == "1" && post.AcceptedAnswerId == answerPost.Id) {
                    User u_p = findUser(answerPost.OwnerUserId);
                    acceptedAnswers[u_p.Id].DisplayName = u_p.DisplayName;
                    acceptedAnswers[u_p.Id].Count++;
                }
            }
        }
    }

    cout << "Top 10 users with the most accepted answers:" <<endl;
    for (int i = 0; i < 10; i++) {
        string key;
        MapData max_data = { "", 0 };
        for (map<string, MapData>::iterator it = acceptedAnswers.begin();
             it != acceptedAnswers.end(); ++it) {
            if (it->second.Count >= max_data.Count) {
                key = it->first;
                max_data = it->second;
            }
        }

        acceptedAnswers.erase(key);
        cout << max_data.Count << '\t' << max_data.DisplayName <<endl;
    }

    cout <<endl;

    return 0;
}
