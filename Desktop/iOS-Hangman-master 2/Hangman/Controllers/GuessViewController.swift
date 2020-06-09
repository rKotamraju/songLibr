//
//  GuessViewController.swift
//  Hangman
//
//  Created by Sujit Molleti on 5/20/20.
//  Copyright © 2020 Sujit Molleti. All rights reserved.
//

import UIKit

class GuessViewController: UIViewController {
    
    var word: String?
    var blankWord: String = ""
    var definition: String = "Definition: ..."
    
    var WordJSON: Word?
    
    
    var imageArray: [UIImage?] = [
        UIImage(named: "Hook"),
        UIImage(named: "Head"),
        UIImage(named: "Body"),
        UIImage(named: "LegOne"),
        UIImage(named: "LegTwo"),
        UIImage(named: "GameOver")
    ]
    
    @IBOutlet weak var guessWordLabel: UILabel!
    @IBOutlet weak var guessProgressView: UIProgressView!
    //progressView has a min: 0.0, max: 1
    @IBOutlet weak var hangImageView: UIImageView!
    @IBOutlet weak var definitionLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Guess"
        setUI()
    }
    
    func setUI(){
        for _ in 0..<word!.count{
            blankWord += "-"
        }
        
        guessWordLabel.text = blankWord
        guessProgressView.progress = 0
        hangImageView.image = imageArray[0]
        
        parseJSONForDef { (Word) in
            
            self.WordJSON = Word
            //print(self.definition)
            DispatchQueue.main.async { //ask sal  running on the main thread?
                self.definitionLabel.text = "Definition: \(self.definition)"
            }
            
        }
        
        definitionLabel.text = definition
    }
    
    @IBAction func letterTapped(_ sender: UIButton){
        
        let letter: String = sender.currentTitle!
        
        if(( word?.contains(letter)) == true) { //good guess
            //print(true)
            //flip the dash
            flipDash(letter)
            //disable the button
            disableButton(sender)
        }else{ //bad guess
            //change progressView
            guessProgressView.setProgress(guessProgressView.progress + 0.2, animated: true)
            //change the image
            
            let imageIndex: Int = Int((guessProgressView.progress * 5))
            hangImageView.image = imageArray[imageIndex]
            
            //disable the button
            disableButton(sender)
        }
        
        gameOver()
        
    }
    
    func gameOver() -> Void {
        
        var gameOver = false
        var titleStr = ""
        var messageStr = ""
        
        //guesser won
        if(blankWord.contains("-") == false){
            titleStr = "Guesser Won!"
            messageStr = "Would you like to play again?"
            gameOver = true
        }
        
        //guesser lost
        if(guessProgressView.progress == 1){
            titleStr = "Guesser Lost."
            messageStr = "The word was \(String(describing: word)). Would you like to play again?"
            gameOver = true
        }
        
        if(gameOver){
            let alert = UIAlertController(title: titleStr, message: messageStr, preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "Yes", style: .default, handler: { _ in
                //print("Yes Pressed")
                //go to wordSelection Screen
                self.navigationController?.popViewController(animated: true)
            }))
            alert.addAction(UIAlertAction(title: "No", style: .cancel, handler: { _ in
                //print("No Pressed")
                //go to title screen
                self.navigationController?.popToRootViewController(animated: true)
            }))
            self.present(alert, animated: true, completion: nil)
        }
        
    }
    
    func disableButton(_ sender: UIButton){
        sender.isEnabled = false
        sender.backgroundColor = UIColor.systemGray4
    }
    
    func flipDash(_ letter: String){
        
    
        //Find the index(s) in word
        
        let letterChar: Character = letter.first!
        var temp: String = ""
        var index = word!.startIndex
        
        for char in word! {
            if(char == letterChar){
                temp += "\(word![index])"

            }else{
                temp += "\(blankWord[index])"
                //print("no match")
            }
            index = word!.index(after: index)
        }
        
        //change blankWord
        blankWord = temp
        
        //reloadTheLabel
        guessWordLabel.text = blankWord
        
    }
    
    func parseJSONForDef(completion: @escaping (Word)-> ()){
        let appId = "5886c1ac"
        let appKey = "6398510576687de8b0a444904d422f20"
        let language = "en-us"
        let word = self.word!
        let fields = "definitions"
        let strictMatch = "false"
        let word_id = word.lowercased()
        
        //start building the url
        let urlString = "https://od-api.oxforddictionaries.com:443/api/v2/entries/\(language)/\(word_id)?fields=\(fields)&strictMatch=\(strictMatch)"
        let url = URL(string: urlString)
        
        //checking if the url is not nil
        guard url != nil else{
            return
        }
        
        //setting permissions for the request
        var request = URLRequest(url: url!)
        request.addValue(appId, forHTTPHeaderField: "app_id")
        request.addValue(appKey, forHTTPHeaderField: "app_key")
        
        let session = URLSession.shared
        
        //double-click to create closure
        let dataTask =  session.dataTask(with: request) { (data, response, error) in
            
            if error == nil && data != nil {
                //parse data
                let decoder = JSONDecoder()
                do {
                    
                    let word  = try decoder.decode(Word.self, from: data!)
                    
                    self.definition = word.results?[0].lexicalEntries?[0].entries?[0].senses?[0].definitions?[0] ?? "NA"
                    
                    //
                    completion(word)
                    
                    print("success")
                    
                } catch {
                    print("Error in JSON Parsing")
                }
            }
            
            
        }
        
        dataTask.resume()
        
    }
    
}
