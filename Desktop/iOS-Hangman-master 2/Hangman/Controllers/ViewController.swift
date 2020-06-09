//
//  ViewController.swift
//  Hangman
// Rachana's commit
//  Created by Sujit Molleti on 5/20/20.
//  Copyright © 2020 Sujit Molleti. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        //parseJSON()
        
    }
    
//    func parseJSON(){
//        let appId = "5886c1ac"
//        let appKey = "6398510576687de8b0a444904d422f20"
//        let language = "en-us"
//        let word = "run"
//        let fields = "definitions"
//        //let strictMatch = "false"
//        let word_id = word.lowercased()
//
//        //start building the url
//        let urlString = "https://od-api.oxforddictionaries.com:443/api/v2/entries/\(language)/\(word_id)?fields=\(fields)"
//        let url = URL(string: urlString)
//
//        //checking if the url is not nil
//        guard url != nil else{
//            return
//        }
//
//        //setting permissions for the request
//        var request = URLRequest(url: url!)
//        request.addValue(appId, forHTTPHeaderField: "app_id")
//        request.addValue(appKey, forHTTPHeaderField: "app_key")
//
//        let session = URLSession.shared
//
//        //double-click to create closure
//        let dataTask =  session.dataTask(with: request) { (data, response, error) in
//
//            if error == nil && data != nil {
//                //parse data
//                let decoder = JSONDecoder()
//                do {
//
//                    let word  = try decoder.decode(Word.self, from: data!)
//
//                    print(word.results?[0].lexicalEntries?[0].entries?[0].senses?[0].definitions?[0] ?? "error")
//
//                } catch {
//                    print("Error in JSON Parsing")
//                }
//            }
//
//
//        }
//
//        dataTask.resume()
//    }
    
}

