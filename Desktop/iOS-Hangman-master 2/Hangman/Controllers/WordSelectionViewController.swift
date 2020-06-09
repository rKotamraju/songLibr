//
//  WordSelectionViewController.swift
//  Hangman
//
//  Created by Sujit Molleti on 5/20/20.
//  Copyright © 2020 Sujit Molleti. All rights reserved.
//

import UIKit

class WordSelectionViewController: UIViewController {

    @IBOutlet weak var wordTextField: UITextField!
    var wordText: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Pick a word"
        setUI()
    }
        
    func setUI(){
        self.wordTextField.text = ""
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "SelectionToGuess") {
            let destVC = segue.destination as! GuessViewController
            destVC.word = sender as? String
        }
        
    }
    
    @IBAction func confirmTapped(_ sender: Any) {
        
        //dictionaryCall()
        
        wordText = wordTextField.text?.uppercased()
                
        wordText = wordText?.trimmingCharacters(in: .whitespaces)
        if(wordText?.count == 0){
            let alert = UIAlertController(title: "Empty String", message: "Your word is BLANK!", preferredStyle: .actionSheet)
            alert.addAction(UIAlertAction(title: "OK", style: .cancel, handler: { _ in
                self.wordTextField.text = ""
            }))
            
            self.present(alert, animated: true)
        }
        performSegue(withIdentifier: "SelectionToGuess", sender: wordText)
        
    }
    
    @IBAction func randomTapped(_ sender: Any) {
        
        var randomWord: String?
        
        getRandomWord { (RandomWord) in
            DispatchQueue.main.async {
                print(RandomWord)
                randomWord = RandomWord.word!.uppercased()
                print(randomWord)
                self.performSegue(withIdentifier: "SelectionToGuess", sender: randomWord)
            }
        }
        
        
    }
    
    func getRandomWord(completion: @escaping (RandomWord) -> ()){
        
        let apiKey = "axzem80of00hr6hgf3i6tmoikuo37sdsq8ja37p1eojbk00i8"
        let maxCorpusCount = -1
        let minDictionaryCount = 1
        let maxDictionaryCount = -1
        let minLength = 5
        let maxLength = -1
        
        let urlString = "https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&maxCorpusCount=\(maxCorpusCount)&minDictionaryCount=\(minDictionaryCount)&maxDictionaryCount=\(maxDictionaryCount)&minLength=\(minLength)&maxLength=\(maxLength)&api_key=\(apiKey)"

        
        let url = URL(string: urlString)
        
        if(url == nil){
            return
        }
        
        let request = URLRequest(url: url!)
        
        let session = URLSession.shared
        
        print("hi")
        
        session.dataTask(with: request) { (data, response, error) in
            if(data != nil && error == nil){
                
                let decoder = JSONDecoder()
                
                do{
                   let randomWord = try decoder.decode(RandomWord.self, from: data!)
                    completion(randomWord)
                    print("Success")
                } catch{
                    print("Error")
                }
                
                
            }else{
                print(error)
            }
            
        }.resume()
        
        
        
    }
    
    
    
}
